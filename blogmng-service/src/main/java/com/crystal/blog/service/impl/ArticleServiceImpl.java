package com.crystal.blog.service.impl;

import com.crystal.blog.common.bean.param.ArticleParam;
import com.crystal.blog.common.bean.param.ArticleQueryParam;
import com.crystal.blog.common.bean.response.ArticleVO;
import com.crystal.blog.common.bean.response.TagVO;
import com.crystal.blog.common.bean.response.UserVO;
import com.crystal.blog.common.bean.response.base.PageInfo;
import com.crystal.blog.common.enums.ArticleStatusEnum;
import com.crystal.blog.common.enums.StatusEnum;
import com.crystal.blog.common.util.AuthUtil;
import com.crystal.blog.common.util.BeanUtil;
import com.crystal.blog.dao.mapper.ArticleContentMapper;
import com.crystal.blog.dao.mapper.ArticleMapper;
import com.crystal.blog.dao.mapper.ArticleTagMapper;
import com.crystal.blog.dao.mapper.TagMapper;
import com.crystal.blog.dao.model.*;
import com.crystal.blog.service.ArticleService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.thymeleaf.util.DateUtils;

import java.util.List;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleContentMapper articleContentMapper;

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Override
    @Transactional
    public Integer save(ArticleParam articleParam) {
        if (articleParam.getId() != null) { // 修改
            updateArticle(articleParam);
        } else { // 添加
            addArticle(articleParam);
        }
        return articleParam.getId();
    }

    /**
     * 添加文章
     * @return
     */
    private Boolean addArticle(ArticleParam articleParam) {
        UserVO currentUser = AuthUtil.getCurrentUser();
        // 保存文章内容
        ArticleContent content = new ArticleContent();
        content.setContent(articleParam.getContent());
        content.setStatus(StatusEnum.NORMAL.getCode());
        articleContentMapper.insert(content);

        // 保存文章
        Article article = BeanUtil.transfer(articleParam, Article.class);
        article.setContentId(content.getId());
        article.setUserId(currentUser.getId());
        articleMapper.insertSelective(article);

        // 保存标签
        articleParam.setId(article.getId());
        saveArticleTag(articleParam, currentUser);
        return true;
    }


    /**
     * 修改文章
     * @return
     */
    private Boolean updateArticle(ArticleParam articleParam) {
        UserVO currentUser = AuthUtil.getCurrentUser();
        // 保存文章内容
        ArticleContent content = new ArticleContent();
        content.setId(articleParam.getContentId());
        content.setContent(articleParam.getContent());
        articleContentMapper.updateByPrimaryKeySelective(content);

        // 保存文章
        Article article = BeanUtil.transfer(articleParam, Article.class);
        articleMapper.updateByPrimaryKeySelective(article);

        // 删除文章标签关联
        ArticleTagExample articleTagExample = new ArticleTagExample();
        articleTagExample.createCriteria().andArticleIdEqualTo(articleParam.getId());
        articleTagMapper.deleteByExample(articleTagExample);

        // 保存标签
        saveArticleTag(articleParam, currentUser);
        return true;
    }

    /**
     * 保存标签和文章标签关系
     * @param articleParam
     * @param currentUser
     */
    private void saveArticleTag(ArticleParam articleParam, UserVO currentUser) {
        if (!StringUtils.isEmpty(articleParam.getTags())) {
            String[] tags = articleParam.getTags().split(",");

            for (String tag : tags) {
                TagExample example = new TagExample();
                example.createCriteria().andUserIdEqualTo(currentUser.getId()).andTagEqualTo(tag);
                long count = tagMapper.countByExample(example);
                if (count == 0) { // 添加标签
                    Tag tagModel = new Tag();
                    tagModel.setTag(tag);
                    tagModel.setUserId(currentUser.getId());
                    tagMapper.insert(tagModel);
                }
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTag(tag);
                articleTag.setArticleId(articleParam.getId());
                articleTagMapper.insert(articleTag);
            }
        }
    }

    @Override
    public ArticleVO queryDetail(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        ArticleVO result = null;
        if (article != null) {
            result = BeanUtil.transfer(article, ArticleVO.class);
            ArticleContent content = articleContentMapper.selectByPrimaryKey(article.getContentId());
            result.setContent(content.getContent());

            ArticleTagExample articleTagExample = new ArticleTagExample();
            articleTagExample.createCriteria().andArticleIdEqualTo(id);
            List<ArticleTag> tags = articleTagMapper.selectByExample(articleTagExample);
            List<TagVO> tagVos = BeanUtil.transferList(tags, TagVO.class);
            result.setTags(tagVos);
            if (CollectionUtils.isEmpty(tags)) {
                StringBuffer tagsStr = new StringBuffer();
                for (ArticleTag tag: tags) {
                    tagsStr.append(tag + ",");
                }
                result.setTagsStr(tagsStr.substring(0, tagsStr.length()-2));
            }
        } else {
            result = new ArticleVO();
        }
        return result;
    }

    @Override
    public PageInfo<ArticleVO> queryArticleListWithPage(ArticleQueryParam articleQueryParam) {
        UserVO userVO = AuthUtil.getCurrentUser();
        if (userVO != null) {
            articleQueryParam.setUserId(userVO.getId());
        }
        Page<Article> page = articleMapper.queryArticleListWithPage(articleQueryParam);
        PageInfo<ArticleVO> result = BeanUtil.transferPage(page, ArticleVO.class);
        if (!CollectionUtils.isEmpty(result.getResult())) {
            for (ArticleVO articleVO: result.getResult()) {
                ArticleTagExample articleTagExample = new ArticleTagExample();
                articleTagExample.createCriteria().andArticleIdEqualTo(articleVO.getId());
                List<ArticleTag> tags = articleTagMapper.selectByExample(articleTagExample);
                List<TagVO> tagVos = BeanUtil.transferList(tags, TagVO.class);
                articleVO.setTags(tagVos);
            }
        }
        return result;
    }

    @Override
    public List<ArticleVO> queryArticleList(Integer year) {
        UserVO userVO = AuthUtil.getCurrentUser();
        ArticleExample example = new ArticleExample();
        ArticleExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(ArticleStatusEnum.PUBLISH.getCode());
        if (userVO != null) {
            criteria.andUserIdEqualTo(userVO.getId());
        }

        criteria.andCreateTimeBetween(DateUtils.create(year, 1, 1).getTime(), DateUtils.create(year + 1, 1, 1).getTime());
        example.setOrderByClause(" update_time desc, create_time desc");
        List<Article> articles = articleMapper.selectByExample(example);
        List<ArticleVO> articleVOS = BeanUtil.transferList(articles, ArticleVO.class);
        return articleVOS;
    }

    @Override
    public List<ArticleVO> queryHotArticleList() {
        UserVO userVO = AuthUtil.getCurrentUser();
        Integer userId = null;
        if (userVO != null) {
            userId = userVO.getId();
        }
        List<Article> articleList = articleMapper.queryHotArticleList(userId);
        List<ArticleVO> articleVOS = BeanUtil.transferList(articleList, ArticleVO.class);
        return articleVOS;
    }

    @Override
    public List<ArticleVO> queryNewArticleList() {
        ArticleQueryParam articleQueryParam = new ArticleQueryParam();
        articleQueryParam.setStatus(ArticleStatusEnum.PUBLISH.getCode());
        UserVO userVO = AuthUtil.getCurrentUser();
        if (userVO != null) {
            articleQueryParam.setUserId(userVO.getId());
        }
        articleQueryParam.setPageSize(5);
        Page<Article> page = articleMapper.queryArticleListWithPage(articleQueryParam);
        List<ArticleVO> result = BeanUtil.transferList(page.getResult(), ArticleVO.class);
        return result;
    }
}

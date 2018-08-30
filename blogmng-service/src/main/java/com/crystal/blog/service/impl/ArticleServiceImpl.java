package com.crystal.blog.service.impl;

import com.crystal.blog.common.bean.param.ArticleParam;
import com.crystal.blog.common.bean.response.ArticleVO;
import com.crystal.blog.common.bean.response.UserVO;
import com.crystal.blog.common.enums.StatusEnum;
import com.crystal.blog.common.util.AuthUtil;
import com.crystal.blog.common.util.BeanUtil;
import com.crystal.blog.dao.mapper.ArticleContentMapper;
import com.crystal.blog.dao.mapper.ArticleMapper;
import com.crystal.blog.dao.mapper.ArticleTagMapper;
import com.crystal.blog.dao.mapper.TagMapper;
import com.crystal.blog.dao.model.*;
import com.crystal.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
        content.setCreateBy(currentUser.getLoginName());
        content.setUpdateBy(currentUser.getLoginName());
        articleContentMapper.insert(content);

        // 保存文章
        Article article = BeanUtil.transfer(articleParam, Article.class);
        article.setContentId(content.getId());
        article.setCreateBy(currentUser.getLoginName());
        article.setUpdateBy(currentUser.getLoginName());
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
        ArticleContent content = BeanUtil.transfer(articleParam, ArticleContent.class);
        content.setUpdateBy(currentUser.getLoginName());
        articleContentMapper.updateByPrimaryKey(content);

        // 保存文章
        Article article = BeanUtil.transfer(articleParam, Article.class);
        article.setUpdateBy(currentUser.getLoginName());
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
                    tagModel.setCreateBy(currentUser.getLoginName());
                    tagMapper.insert(tagModel);
                }
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTag(tag);
                articleTag.setArticleId(articleParam.getId());
                articleTag.setCreateBy(currentUser.getLoginName());
                articleTagMapper.insert(articleTag);
            }
        }
    }

    @Override
    public ArticleVO queryDetail(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        ArticleVO result = BeanUtil.transfer(article, ArticleVO.class);
        ArticleContent content = articleContentMapper.selectByPrimaryKey(article.getContentId());
        result.setContent(content.getContent());
        return result;
    }
}

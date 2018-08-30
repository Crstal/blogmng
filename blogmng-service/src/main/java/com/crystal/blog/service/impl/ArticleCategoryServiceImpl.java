package com.crystal.blog.service.impl;

import com.crystal.blog.common.bean.response.ArticleCategoryVO;
import com.crystal.blog.common.bean.response.TagVO;
import com.crystal.blog.common.bean.response.UserVO;
import com.crystal.blog.common.util.AuthUtil;
import com.crystal.blog.common.util.BeanUtil;
import com.crystal.blog.dao.mapper.ArticleCategoryMapper;
import com.crystal.blog.dao.mapper.TagMapper;
import com.crystal.blog.dao.model.ArticleCategory;
import com.crystal.blog.dao.model.ArticleCategoryExample;
import com.crystal.blog.dao.model.Tag;
import com.crystal.blog.dao.model.TagExample;
import com.crystal.blog.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleCategoryService")
public class ArticleCategoryServiceImpl implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryMapper articleCategoryMapper;

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<ArticleCategoryVO> selectCategoryList() {
        ArticleCategoryExample example = new ArticleCategoryExample();
        List<ArticleCategory> categories = articleCategoryMapper.selectByExample(example);
        List<ArticleCategoryVO> result = BeanUtil.transferList(categories, ArticleCategoryVO.class);
        return result;
    }

    public List<TagVO> selectTagList() {
        UserVO userVO = AuthUtil.getCurrentUser();
        TagExample example = new TagExample();
        example.createCriteria().andUserIdEqualTo(userVO.getId());
        List<Tag> categories = tagMapper.selectByExample(example);
        List<TagVO> result = BeanUtil.transferList(categories, TagVO.class);
        return result;
    }
}

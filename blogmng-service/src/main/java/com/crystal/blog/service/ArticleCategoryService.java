package com.crystal.blog.service;

import com.crystal.blog.common.bean.response.ArticleCategoryVO;
import com.crystal.blog.common.bean.response.TagVO;

import java.util.List;

public interface ArticleCategoryService {

    /**
     * 查询文章分类
     * @return
     */
    List<ArticleCategoryVO> selectCategoryList(Integer parentId);

    /**
     * 查询当前用户下的所有标签
     * @return
     */
    List<TagVO> selectTagList();
}

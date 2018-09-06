package com.crystal.blog.dao.mapper;

import com.crystal.blog.common.bean.param.ArticleQueryParam;
import com.crystal.blog.dao.mapper.base.BaseMapper;
import com.crystal.blog.dao.model.Article;
import com.crystal.blog.dao.model.ArticleExample;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* Created by Mybatis Generator on 2018/08/29
*/
public interface ArticleMapper extends BaseMapper<Article, ArticleExample, Integer> {

    /**
     * 根据条件查询文章列表
     * @param queryParam
     * @return
     */
    Page<Article> queryArticleListWithPage(ArticleQueryParam queryParam);

    /**
     * 查询热门文章 前5条
     * @return
     */
    List<Article> queryHotArticleList(@Param(value = "userId") Integer userId);
}
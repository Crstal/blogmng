package com.crystal.blog.dao.mapper;

import com.crystal.blog.common.bean.param.ArticleQueryParam;
import com.crystal.blog.dao.mapper.base.BaseMapper;
import com.crystal.blog.dao.model.Article;
import com.crystal.blog.dao.model.ArticleExample;
import com.github.pagehelper.Page;

/**
* Created by Mybatis Generator on 2018/08/29
*/
public interface ArticleMapper extends BaseMapper<Article, ArticleExample, Integer> {

    Page<Article> queryArticleListWithPage(ArticleQueryParam queryParam);
}
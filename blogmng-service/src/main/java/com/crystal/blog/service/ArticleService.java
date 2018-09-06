package com.crystal.blog.service;

import com.crystal.blog.common.bean.param.ArticleParam;
import com.crystal.blog.common.bean.param.ArticleQueryParam;
import com.crystal.blog.common.bean.response.ArticleVO;
import com.crystal.blog.common.bean.response.base.PageInfo;

import java.util.List;

/**
* @Author: caoyue
* @Description: 文章管理
* @Date: 17:53 2018/8/27
**/
public interface ArticleService {

    /**
     * 保存文章
     * @param articleParam
     * @return
     */
    Integer save(ArticleParam articleParam);

    /**
     * 查询博客详情
     * @param id
     * @return
     */
    ArticleVO queryDetail(Integer id);

    /**
     * 分页查询博客列表
     * @param articleQueryParam
     * @return
     */
    PageInfo<ArticleVO> queryArticleListWithPage(ArticleQueryParam articleQueryParam);

    /**
     * 查询某一年博客列表
     * @param year
     * @return
     */
    List<ArticleVO> queryArticleList(Integer year);

    /**
     * 查询热门博客列表
     * @return
     */
    List<ArticleVO> queryHotArticleList();

    /**
     * 查询最新博客列表
     * @return
     */
    List<ArticleVO> queryNewArticleList();
}

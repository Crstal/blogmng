package com.crystal.blog.service;

import com.crystal.blog.common.bean.param.ArticleParam;
import com.crystal.blog.common.bean.param.ArticleQueryParam;
import com.crystal.blog.common.bean.response.ArticleVO;
import com.crystal.blog.common.bean.response.base.PageInfo;

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

    ArticleVO queryDetail(Integer id);


    PageInfo<ArticleVO> queryArticleListWithPage(ArticleQueryParam articleQueryParam);


}

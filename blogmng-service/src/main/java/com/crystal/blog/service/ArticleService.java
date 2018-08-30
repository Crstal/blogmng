package com.crystal.blog.service;

import com.crystal.blog.common.bean.param.ArticleParam;
import com.crystal.blog.common.bean.response.ArticleVO;

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
    Boolean save(ArticleParam articleParam);

    ArticleVO queryByPrimaryKey(Integer id);


}

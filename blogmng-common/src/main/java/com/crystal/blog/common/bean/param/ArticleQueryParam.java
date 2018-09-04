package com.crystal.blog.common.bean.param;

import com.crystal.blog.common.bean.param.base.PageParam;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleQueryParam extends PageParam {

    /**
     * 含义：用户id
     */
    private Integer userId;

    /**
     * 含义：文章类型
     */
    private String categoryCode;

    private String categoryName;

    /**
     * 标签
     */
    private String tag;

    /**
     * 含义：文章标题
     */
    private String title;

    /**
     * 含义：文章状态：1发布 2私有 3草稿 4回收
     */
    @NotNull
    private Integer status;

    private String search;
}

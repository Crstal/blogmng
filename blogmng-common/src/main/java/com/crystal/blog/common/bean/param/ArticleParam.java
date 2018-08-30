package com.crystal.blog.common.bean.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ArticleParam implements Serializable {
    private Integer id;

    /**
     * 含义：用户id
     */
    private Integer userId;

    /**
     * 含义：文章类型
     */
    @NotNull(message = "文章类型不能为空")
    private Long categoryCode;

    /**
     * 含义：文章类型
     */
    private String categoryName;

    /**
     * 标签
     */
    private String tags;

    /**
     * 含义：文章标题
     */
    @NotNull(message = "文章标题不能为空")
    private String title;

    /**
     * 含义：文章内容id
     */
    private String contentId;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 含义：文章状态：1发布 2私有 3草稿 4回收
     */
    @NotNull
    private Integer status;

    private static final long serialVersionUID = 1L;
}

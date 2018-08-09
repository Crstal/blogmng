package com.crystal.blog.dao.model;

import com.crystal.blog.dao.model.base.BaseModel;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2018/08/09
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseModel implements Serializable {
    /**
     * 字段：user_id
     * 含义：用户id
     */
    private Integer userId;

    /**
     * 字段：category_id
     * 含义：文章类型
     */
    private Long categoryId;

    /**
     * 字段：title
     * 含义：文章标题
     */
    private String title;

    /**
     * 字段：pre_article_id
     * 含义：上一篇文章id
     */
    private Integer preArticleId;

    /**
     * 字段：pre_article_title
     * 含义：上一篇文章标题
     */
    private String preArticleTitle;

    /**
     * 字段：next_article_id
     * 含义：下一篇文章id
     */
    private Integer nextArticleId;

    /**
     * 字段：next_article_title
     * 含义：下一篇文章标题
     */
    private String nextArticleTitle;

    /**
     * 字段：content_id
     * 含义：文章内容id
     */
    private String contentId;

    /**
     * 字段：status
     * 含义：文章状态：1发布 2私有 3草稿 4回收
     */
    private Integer status;

    /**
     * 字段：follow_count
     * 含义：关注数量
     */
    private Integer followCount;

    /**
     * 字段：comment_count
     * 含义：评论数量
     */
    private Integer commentCount;

    /**
     * 字段：visit_count
     * 含义：浏览数量
     */
    private Integer visitCount;

    private static final long serialVersionUID = 1L;
}
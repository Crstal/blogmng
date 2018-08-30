package com.crystal.blog.dao.model;

import com.crystal.blog.dao.model.base.BaseModel;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2018/08/29
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFollowArticle extends BaseModel implements Serializable {
    /**
     * 字段：user_id
     * 含义：用户id
     */
    private Integer userId;

    /**
     * 字段：follow_article_Id
     * 含义：关注文章id
     */
    private Integer followArticleId;

    /**
     * 字段：follow_article_title
     * 含义：关注文章标题
     */
    private String followArticleTitle;

    private static final long serialVersionUID = 1L;
}
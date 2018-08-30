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
public class ArticleTag extends BaseModel implements Serializable {
    /**
     * 字段：article_id
     * 含义：文章id
     */
    private Integer articleId;

    /**
     * 字段：tag
     * 含义：标签
     */
    private String tag;

    private static final long serialVersionUID = 1L;
}
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
public class ArticleContent extends BaseModel implements Serializable {
    /**
     * 字段：status
     * 含义：状态 1正常 2删除
     */
    private Integer status;

    /**
     * 字段：content
     * 含义：内容
     */
    private String content;

    private static final long serialVersionUID = 1L;
}
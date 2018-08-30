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
public class ArticleCategory extends BaseModel implements Serializable {
    /**
     * 字段：parent_id
     * 含义：父类型
     */
    private Integer parentId;

    /**
     * 字段：code
     * 含义：文章类型
     */
    private String code;

    /**
     * 字段：name
     * 含义：类型名称
     */
    private String name;

    private static final long serialVersionUID = 1L;
}
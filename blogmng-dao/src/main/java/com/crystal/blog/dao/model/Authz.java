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
public class Authz extends BaseModel implements Serializable {
    /**
     * 字段：code
     * 含义：权限编号
     */
    private String code;

    /**
     * 字段：description
     * 含义：权限说明
     */
    private String description;

    /**
     * 字段：type
     * 含义：权限类型：1功能 2requestUrl
     */
    private Integer type;

    private static final long serialVersionUID = 1L;
}
package com.crystal.blog.dao.model;

import com.crystal.blog.dao.model.base.BaseModel;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* Created by Mybatis Generator on 2019/10/12
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ThemeEffect extends BaseModel implements Serializable {
    /**
     * 字段：name
     * 含义：时间名称
     */
    private String name;

    /**
     * 字段：css_url
     * 含义：样式地址
     */
    private String cssUrl;

    /**
     * 字段：script_url
     * 含义：脚本地址
     */
    private String scriptUrl;

    /**
     * 字段：type
     * 含义：类型：1可以重复 0不能重复
     */
    private Integer type;

    /**
     * 字段：status
     * 含义：使用状态：0未使用 1使用中
     */
    private Integer status;

    private static final long serialVersionUID = 1L;
}
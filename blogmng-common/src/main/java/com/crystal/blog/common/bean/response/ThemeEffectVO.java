package com.crystal.blog.common.bean.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ThemeEffectVO implements Serializable {

    private Integer id;

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
     * 字段：status
     * 含义：使用状态：0未使用 1使用中
     */
    private Integer status;
}

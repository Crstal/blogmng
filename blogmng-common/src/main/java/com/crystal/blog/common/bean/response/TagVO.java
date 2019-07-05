package com.crystal.blog.common.bean.response;

import lombok.Data;

/**
* @Author: caoyue
* @Description: 标签
* @Date: 17:31 2018/8/29
**/
@Data
public class TagVO {

    private Integer id;

    private Integer userId;

    /**
     * 含义：标签类型
     */
    private String tag;

    private boolean checked = false;
}

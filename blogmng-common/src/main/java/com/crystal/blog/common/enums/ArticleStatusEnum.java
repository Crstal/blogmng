package com.crystal.blog.common.enums;

/**
* @Author: caoyue
* @Description: 文章状态
* @Date: 17:34 2018/8/27
**/
public enum ArticleStatusEnum {

    PUBLISH(1, "发布"),
    PRIVATE(2, "私有"),
    DRAFT(3, "草稿"),
    DELETE(4, "回收");

    private Integer code;
    private String desc;

    ArticleStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

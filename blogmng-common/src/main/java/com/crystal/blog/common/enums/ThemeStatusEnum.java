package com.crystal.blog.common.enums;

public enum  ThemeStatusEnum {
    NO_USE(0, "未使用"),
    USED(1, "使用中");

    private Integer code;
    private String desc;

    ThemeStatusEnum(Integer code, String desc) {
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
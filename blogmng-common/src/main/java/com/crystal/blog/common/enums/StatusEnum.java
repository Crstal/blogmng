package com.crystal.blog.common.enums;

public enum StatusEnum {
    NORMAL(1, "正常"),
    DELETE(2, "删除");

    private Integer code;
    private String desc;

    StatusEnum(Integer code, String desc) {
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

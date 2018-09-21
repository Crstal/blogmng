package com.crystal.blog.common.enums;

public enum LoginMethodEnum {
    WECHAT(0, "微信登陆"),
    QQ(1, "qq登陆"),
    WEB(2, "正常用户名密码登陆");

    private Integer code;
    private String desc;

    LoginMethodEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getCode() {
        return code;
    }
}

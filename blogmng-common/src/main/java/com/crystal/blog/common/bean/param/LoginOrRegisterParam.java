package com.crystal.blog.common.bean.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LoginOrRegisterParam implements Serializable {

    @NotNull(message = "手机号或邮箱不能为空")
    private String mobileOrEmail;

    @NotNull(message = "密码不能为空")
    private String password;

    private static final long serialVersionUID = 1L;
}

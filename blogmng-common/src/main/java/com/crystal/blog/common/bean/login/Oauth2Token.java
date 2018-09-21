package com.crystal.blog.common.bean.login;

import lombok.Data;

/**
* @Author: caoyue
* @Description: 网页授权信息
* @Date: 9:53 2018/9/7
**/
@Data
public class Oauth2Token {

    // 网页授权接口调用凭证
    private String accessToken;
    // 凭证有效时长
    private int expiresIn;
    // 用于刷新凭证
    private String refreshToken;
    // 用户标识
    private String openId;
     // 用户授权作用域
    private String scope;
}

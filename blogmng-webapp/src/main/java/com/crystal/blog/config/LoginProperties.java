package com.crystal.blog.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "login")
public class LoginProperties {

    @Value("qq.appid")
    private String  qqAppId;

    @Value("qq.appsecret")
    private String qqAppSecret;

    @Value("qq.redirecturl")
    private String qqRedirectUrl;

    @Value("wechat.appid")
    private String weChatAppId;

    @Value("wechat.appsecret")
    private String weChatAppSecret;

    @Value("wechat.redirecturl")
    private String weChatRedirectUrl;

    @Value("wechat.token")
    private String weChatToken;
}

package com.crystal.blog.common.bean.login;

import lombok.Data;
import lombok.Getter;

/**
 * 微信通用接口凭证
 * 
 * @author liufeng
 * @date 2013-08-08
 */
@Getter
public class AccessToken {
	// 获取到的凭证
	private final String access_token;
	// 凭证有效时间，单位：秒
	private final int expire_in;

	public AccessToken(String access_token, int expire_in) {
        this.access_token = access_token;
        this.expire_in = expire_in;
    }
}
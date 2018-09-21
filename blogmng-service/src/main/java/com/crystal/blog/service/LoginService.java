package com.crystal.blog.service;

import com.crystal.blog.common.bean.param.LoginOrRegisterParam;
import com.crystal.blog.common.exception.BussinessException;
import com.crystal.blog.sso.bean.Principal;

public interface LoginService {

    /**
     * 验证登陆
     * @param loginOrRegisterParam
     * @return
     */
    Principal verifyLogin(LoginOrRegisterParam loginOrRegisterParam) throws BussinessException;

    /**
     * 注册账号
     * @param param
     * @return
     */
    Boolean register(LoginOrRegisterParam param) throws BussinessException;
}

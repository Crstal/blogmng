package com.crystal.blog.service.impl;

import com.crystal.blog.common.bean.param.LoginOrRegisterParam;
import com.crystal.blog.common.enums.ErrorCode;
import com.crystal.blog.common.exception.BussinessException;
import com.crystal.blog.common.util.BeanUtil;
import com.crystal.blog.common.util.StringUtil;
import com.crystal.blog.dao.model.User;
import com.crystal.blog.service.LoginService;
import com.crystal.blog.service.UserService;
import com.crystal.blog.sso.bean.Principal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServieImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public Principal verifyLogin(LoginOrRegisterParam param) throws BussinessException {
        User user = userService.getUserByMobileOrEmail(param.getMobileOrEmail());
        if (user == null) {
            throw new BussinessException(ErrorCode.FAILURE.getCode(), "手机号或邮箱不存在");
        }
        if (!user.getPassword().equals(param.getPassword())) {
            throw new BussinessException(ErrorCode.FAILURE.getCode(), "密码输入错误");
        }
        Principal principal = BeanUtil.transfer(user, Principal.class);
        return principal;
    }

    @Override
    public Boolean register(LoginOrRegisterParam param) throws BussinessException {
        User user = userService.getUserByMobileOrEmail(param.getMobileOrEmail());
        if (user != null) {
            throw new BussinessException(ErrorCode.FAILURE.getCode(), "该账号已经注册，请直接登陆");
        }
        user = new User();
        if (StringUtil.isMobile(param.getMobileOrEmail())) {
            user.setMobile(param.getMobileOrEmail());
        } else if (StringUtil.isEmail(param.getMobileOrEmail())) {
            user.setEmail(param.getMobileOrEmail());
        } else {
            throw new BussinessException(ErrorCode.FAILURE.getCode(), "手机号或邮箱不存在");
        }
        user.setPassword(param.getPassword());
        return userService.insertOrUpdate(user);
    }
}

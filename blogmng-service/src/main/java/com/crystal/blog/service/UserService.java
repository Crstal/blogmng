package com.crystal.blog.service;

import com.crystal.blog.dao.model.User;

/**
 * 用户
 */
public interface UserService {

    /**
     * 根据openId获取用户信息
     * @param openId
     * @return
     */
    User getUserByOpenId(String openId);

    /**
     * 根据登陆名获取用户信息
     * @param loginName
     * @return
     */
    User getUserByLoginName(String loginName);

    /**
     * 根据手机号或邮箱获取用户
     * @param mobileOrEmail
     * @return
     */
    User getUserByMobileOrEmail(String mobileOrEmail);

    /**
     * 插入记录
     * @param user
     * @return
     */
    Boolean insertOrUpdate(User user);
}

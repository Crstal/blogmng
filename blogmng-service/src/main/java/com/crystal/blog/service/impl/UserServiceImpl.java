package com.crystal.blog.service.impl;

import com.crystal.blog.common.util.StringUtil;
import com.crystal.blog.dao.mapper.UserMapper;
import com.crystal.blog.dao.model.User;
import com.crystal.blog.dao.model.UserExample;
import com.crystal.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByLoginName(String loginName) {
        UserExample example = new UserExample();
        example.createCriteria().andLoginNameEqualTo(loginName);
        List<User> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            log.info("根据用户登陆名获取用户，不存在该用户：{}", loginName);
        } else {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User getUserByOpenId(String openId) {
        UserExample example = new UserExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<User> users = userMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public User getUserByMobileOrEmail(String mobileOrEmail) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (StringUtil.isEmail(mobileOrEmail)) {
            criteria.andEmailEqualTo(mobileOrEmail);
        } else if (StringUtil.isMobile(mobileOrEmail)) {
            criteria.andMobileEqualTo(mobileOrEmail);
        } else {
            return null;
        }
        List<User> users = userMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public Boolean insertOrUpdate(User user) {
        int result;
        if (user.getId() == null) {
            result = userMapper.insert(user);
        } else {
            result = userMapper.updateByPrimaryKey(user);
        }
        return result > 0;
    }
}

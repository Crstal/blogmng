package com.crystal.blog.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSefrvice {

    @Autowired
    private UserMapper userMapper;

    public User selectUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}

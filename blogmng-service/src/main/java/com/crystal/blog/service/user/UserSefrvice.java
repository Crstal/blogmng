package com.crystal.blog.service.user;

import com.crystal.blog.dao.mapper.UserMapper;
import com.crystal.blog.dao.model.User;
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

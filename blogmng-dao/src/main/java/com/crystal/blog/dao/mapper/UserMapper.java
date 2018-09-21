package com.crystal.blog.dao.mapper;

import com.crystal.blog.dao.mapper.base.BaseMapper;
import com.crystal.blog.dao.model.User;
import com.crystal.blog.dao.model.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* Created by Mybatis Generator on 2018/09/12
*/
public interface UserMapper extends BaseMapper<User, UserExample, Integer> {
}
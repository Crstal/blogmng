package com.crystal.blog.dao.mapper;

import com.crystal.blog.dao.mapper.base.BaseMapper;
import com.crystal.blog.dao.model.Comment;
import com.crystal.blog.dao.model.CommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
* Created by Mybatis Generator on 2018/08/29
*/
public interface CommentMapper extends BaseMapper<Comment, CommentExample, Integer> {
}
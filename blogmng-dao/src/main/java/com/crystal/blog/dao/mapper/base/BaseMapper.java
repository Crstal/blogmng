package com.crystal.blog.dao.mapper.base;

import com.crystal.blog.dao.model.base.BaseModel;

public interface  BaseMapper<T extends BaseModel> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(T record);

    int updateByPrimaryKeySelective(T record);
}

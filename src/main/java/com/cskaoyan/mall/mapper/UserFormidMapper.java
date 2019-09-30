package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.UserFormid;

public interface UserFormidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFormid record);

    int insertSelective(UserFormid record);

    UserFormid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFormid record);

    int updateByPrimaryKey(UserFormid record);
}
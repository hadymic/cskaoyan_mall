package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.PermissionDetails;

public interface PermissionDetailsMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(PermissionDetails record);

    int insertSelective(PermissionDetails record);

    PermissionDetails selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(PermissionDetails record);

    int updateByPrimaryKey(PermissionDetails record);
}
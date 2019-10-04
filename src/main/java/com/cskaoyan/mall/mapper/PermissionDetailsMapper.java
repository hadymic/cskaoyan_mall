package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.PermissionDetails;
import com.cskaoyan.mall.vo.permission.Api;

import java.util.List;

public interface PermissionDetailsMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(PermissionDetails record);

    int insertSelective(PermissionDetails record);

    PermissionDetails selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(PermissionDetails record);

    int updateByPrimaryKey(PermissionDetails record);

    List<Api> queryByParentId(int i);
}

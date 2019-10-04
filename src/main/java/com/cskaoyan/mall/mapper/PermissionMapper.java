package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<String> queryPermissionsByRoleId(Integer roleId);

    int deleteByPermissionAndRoleId(@Param("permission") String permission, @Param("roleId") Integer roleId);
}

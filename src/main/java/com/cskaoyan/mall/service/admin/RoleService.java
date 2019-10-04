package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

import com.cskaoyan.mall.vo.permission.PermissionVo;

import com.cskaoyan.mall.vo.BaseValueLabel;
import com.cskaoyan.mall.vo.permission.PermissionsVo;


import java.util.List;

public interface RoleService {
    ListBean<Role> queryRole(Page page, String name);

    List<BaseValueLabel>  roleOptions();

    boolean update(Role role);

    void delete(Role role);

    Role insertRole(Role role);

    PermissionVo rolePermission(int roleId);

    boolean updateRolePermission(PermissionsVo vo);
}

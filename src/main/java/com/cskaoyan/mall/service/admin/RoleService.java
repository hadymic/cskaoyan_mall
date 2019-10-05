package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseValueLabel;
import com.cskaoyan.mall.vo.permission.PermissionVo;
import com.cskaoyan.mall.vo.permission.PermissionsVo;

import java.util.List;

public interface RoleService {
    ListBean<Role> queryRole(Page page, String name);

    List<BaseValueLabel>  roleOptions();

    String update(Role role);

    String delete(Role role);

    Role insertRole(Role role);

    PermissionVo rolePermission(int roleId);

    String updateRolePermission(PermissionsVo vo);
}

package com.cskaoyan.mall.vo.permission;

import java.util.List;

public class PermissionsVo {
    private List<String> permissions;
    private Integer roleId;

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}

package com.cskaoyan.mall.vo.permission;

import java.util.List;

public class PermissionVo {
    List<String> assignedPermissions;
    List<SystemPermission> systemPermissions;

    public List<String> getAssignedPermissions() {
        return assignedPermissions;
    }

    public void setAssignedPermissions(List<String> assignedPermissions) {
        this.assignedPermissions = assignedPermissions;
    }

    public List<SystemPermission> getSystemPermissions() {
        return systemPermissions;
    }

    public void setSystemPermissions(List<SystemPermission> systemPermissions) {
        this.systemPermissions = systemPermissions;
    }
}

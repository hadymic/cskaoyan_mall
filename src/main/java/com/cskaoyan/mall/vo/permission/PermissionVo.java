package com.cskaoyan.mall.vo.permission;

import java.util.List;

public class PermissionVo {
    List<AssignedPermission> assignedPermissions;
   List<SystemPermission>managers;
    public List<AssignedPermission> getAssignedPermissions() {
        return assignedPermissions;
    }

    public void setAssignedPermissions(List<AssignedPermission> assignedPermissions) {
        this.assignedPermissions = assignedPermissions;
    }

    public List<SystemPermission> getManagers() {
        return managers;
    }

    public void setManagers(List<SystemPermission> managers) {
        this.managers = managers;
    }
}

package com.cskaoyan.mall.service.admin.impl;

import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.PermissionDetails;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.mapper.PermissionDetailsMapper;
import com.cskaoyan.mall.mapper.PermissionMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.service.admin.RoleService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;


import com.cskaoyan.mall.vo.permission.*;

import com.cskaoyan.mall.vo.BaseValueLabel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 系统后台管理service实现类
 *
 * @author hadymic
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    PermissionDetailsMapper permissionDetailsMapper;

    @Override
    public ListBean<Role> queryRole(Page page, String name) {
        PageUtils.startPage(page);
        List<Role> roles = roleMapper.queryRole(name);
        return PageUtils.page(roles);
    }

    /**
     * 管理员类目
     *
     * @return
     */
    @Override
    public List<BaseValueLabel> roleOptions() {

        List<Role> roles = roleMapper.roleOptions();
        List<BaseValueLabel> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new BaseValueLabel(role.getId(), role.getName()));
        }

        return list;

    }

    @Override
    public boolean update(Role role) {
        int i = roleMapper.updateByPrimaryKey(role);
        return i == 1;
    }

    @Override
    public void delete(Role role) {
        roleMapper.deleteRloe(role.getId());
    }

    @Override
    public Role insertRole(Role role) {
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());
        role.setDeleted(false);
        roleMapper.insert(role);
        return role;
    }

    @Override
    public PermissionVo rolePermission(int roleId) {
        PermissionVo permissionVo = new PermissionVo();

        List<Api> apiList1 = permissionDetailsMapper.queryByParentId(0);//根据0查询第一层
        List<SystemPermission> systemPermissions = new ArrayList<>();
        for (Api api1 : apiList1) {
            List<Api> apiList2 = permissionDetailsMapper.queryByParentId(api1.getPid());//第二层
            List<ApiPermission> apiPermissions = new ArrayList<>();
            for (Api api2 : apiList2) {
                List<Api> apiList3 = permissionDetailsMapper.queryByParentId(api2.getPid());//第三层
                ApiPermission apiPermission = new ApiPermission();
                apiPermission.setChildren(apiList3);
                apiPermission.setId(api2.getId());
                apiPermission.setLabel(api2.getLabel());
                apiPermissions.add(apiPermission);
            }
            SystemPermission systemPermission = new SystemPermission();
            systemPermission.setChildren(apiPermissions);
            systemPermission.setId(api1.getId());
            systemPermission.setLabel(api1.getLabel());

            systemPermissions.add(systemPermission);
        }
        permissionVo.setSystemPermissions(systemPermissions);

        List<String> strings = permissionMapper.queryPermissionsByRoleId(roleId);
        permissionVo.setAssignedPermissions(strings);
        return permissionVo;
    }

    @Override
    public boolean updateRolePermission(PermissionsVo vo) {
        List<String> permissions = vo.getPermissions();
        List<String> permissionsFromDb = permissionMapper.queryPermissionsByRoleId(vo.getRoleId());
        Iterator<String> iterator = permissions.iterator();
        //去重
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (permissionsFromDb.contains(next)) {
                iterator.remove();
                permissionsFromDb.remove(next);
            }
        }
        //permissionsFromDb剩余要删除的
        for (String permission : permissionsFromDb) {
            permissionMapper.deleteByPermissionAndRoleId(permission, vo.getRoleId());
        }
        //permissions剩余要增加的
        Date date = new Date();
        for (String permission : permissions) {
            Permission perm = new Permission();
            perm.setPermission(permission);
            perm.setRoleId(vo.getRoleId());
            perm.setAddTime(date);
            perm.setUpdateTime(date);
            perm.setDeleted(false);
            permissionMapper.insertSelective(perm);
        }
        return true;
    }


}

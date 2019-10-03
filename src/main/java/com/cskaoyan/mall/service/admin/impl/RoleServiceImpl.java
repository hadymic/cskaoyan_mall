package com.cskaoyan.mall.service.admin.impl;

import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.mapper.PermissionMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.service.admin.RoleService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.CategoryVo;
import com.cskaoyan.mall.vo.goodsMangement.BaseValueLabel;
import com.cskaoyan.mall.vo.permission.AssignedPermission;
import com.cskaoyan.mall.vo.permission.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
    @Override
    public ListBean<Role> queryRole(Page page, String name) {
        PageUtils.startPage(page);
        List<Role> roles=  roleMapper.queryRole(name);
        return PageUtils.page(roles);
    }

    /**
     * 管理员类目
     * @return
     */
    @Override
    public List<BaseValueLabel> roleOptions() {

        List<Role> roles = roleMapper.roleOptions();
        List<BaseValueLabel> list= new ArrayList<>();
        for (Role role:roles) {
            list.add(new BaseValueLabel(role.getId(),role.getName()));
        }

        return  list;

    }

    @Override
    public int update(Role role) {

        int i = roleMapper.updateByPrimaryKey(role);
        return 1;
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
        return  role;
    }

    @Override
    public PermissionVo rolePermission(int roleId, List<Permission> permissions) {
       List resultList = new ArrayList();
        AssignedPermission assignedPermission= roleMapper.queryAll();
        return null;
    }


}

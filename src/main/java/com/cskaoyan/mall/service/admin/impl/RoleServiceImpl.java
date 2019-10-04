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
        return role;
    }

    @Override
    public PermissionVo rolePermission(int roleId) {
        List<SystemPermission> systemPermissionList = new ArrayList<>();
        PermissionVo permissionVo = new PermissionVo();
        List<Api> apisList1 = permissionDetailsMapper.queryByParentId(0);//根据0查询第一层
        List<ApiMessage> apiMessageList;
        SystemPermission  systemPermission;
        for (Api api1 : apisList1) {
            systemPermission = new SystemPermission();
            List<Api> apiList2 = permissionDetailsMapper.queryByParentId(api1.getPid());//第二层
            apiMessageList = new ArrayList<>();
            List<Api> apis3 ;
            ApiMessage apiMessage = new ApiMessage();
            for (Api api2 : apiList2) {
                apis3 = permissionDetailsMapper.queryByParentId(api2.getPid());//第三层
                apiMessage.setChildren(apis3);
            }
            apiMessage.setId(api1.getId());
            apiMessage.setLabel(api1.getLabel());
            apiMessageList.add(apiMessage);

            systemPermission.setChildren(apiMessageList);
            systemPermission.setLabel(api1.getLabel());
            systemPermission.setId(api1.getId());

            systemPermissionList.add(systemPermission);
        }
        permissionVo.setManagers(systemPermissionList);
        return permissionVo;
    }


}

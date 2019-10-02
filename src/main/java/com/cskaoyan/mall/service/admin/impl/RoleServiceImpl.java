package com.cskaoyan.mall.service.admin.impl;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.service.admin.RoleService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.CategoryVo;
import com.cskaoyan.mall.vo.goodsMangement.BaseValueLabel;
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
        List<BaseValueLabel> options= roleMapper.roleOptions();
        return options;

    }

    @Override
    public int update(Role role) {

        int i = roleMapper.updateByPrimaryKey(role);
        return 1;
    }

    @Override
    public void delete(Role role) {
        int i = roleMapper.deleteRloe(role.getId());
    }

    @Override
    public Role insertRole(Role role) {
        role.setAddTime(new Date());
        role.setUpdateTime(new Date());
        role.setDeleted(false);
        roleMapper.insert(role);
        return  role;
    }


}

package com.cskaoyan.mall.service.admin.impl;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.service.admin.RoleService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ListBean<CategoryVo> roleOptions() {
       List<CategoryVo>roles= roleMapper.roleOptions();

        return null;
    }


}

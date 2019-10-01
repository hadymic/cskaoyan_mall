package com.cskaoyan.mall.service.admin.impl;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.service.admin.AdminService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统后台管理service实现类
 *
 * @author hadymic
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public ListBean<Admin> queryAdmin(Page page,String username) {
        PageUtils.startPage(page);
       List<Admin> admins=  adminMapper.queryAdmin(username);
        return PageUtils.page( admins);
    }

    /**
     * 新增管理员
     * @param admin
     * @return
     */
    @Override
    public List insertAdmin(Admin admin) {
        adminMapper.insert(admin);
        ArrayList list = new ArrayList();
        list.add(admin);
        list.add(admin.getAddTime());
        list.add(admin.getUpdateTime());
        list.add(admin.getId());
        return list;
    }

    @Override
    public ListBean<Role> queryRole(Page page, String name) {
        return null;
    }


}

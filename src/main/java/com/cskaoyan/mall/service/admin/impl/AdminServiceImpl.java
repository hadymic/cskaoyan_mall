package com.cskaoyan.mall.service.admin.impl;

import ch.qos.logback.core.pattern.Converter;
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
import java.util.Date;
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



//        for (Admin admin : admins) {
//            List   list= new ArrayList();
//            list.add(admin.getId());
//            list.add(admin.getUsername());
//            list.add(admin.getAvatar());
//
//            String[] roleIds = admin.getRoleIds();
//            int length = admin.getRoleIds().length;
//            int[] tran=new int[length];
//            for (int i = 0; i <length ; i++) {
//                tran[i]=Integer.parseInt(roleIds[i]);
//            }
//            list.add(tran);
//
//        }
        return PageUtils.page( admins);
    }

    /**
     * 新增管理员
     * @param admin
     * @return
     */
    @Override
    public Admin insertAdmin(Admin admin) {
        admin.setAddTime(new Date());
        admin.setDeleted(false);
        admin.setUpdateTime(new Date());
        adminMapper.insert(admin);
        return  admin;
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @Override
    public int update(Admin admin) {
          admin.setUpdateTime(new Date());
        int update= adminMapper.updateByPrimaryKey(admin);
        return 1;
    }

    /**
     * 删除管理员
     * @param admin
     */
    @Override
    public void delete(Admin admin) {
        int i = adminMapper.deleteAdmin(admin.getId());
    }


}




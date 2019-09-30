package com.cskaoyan.mall.controller.auth;


import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.service.admin.AdminService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台登录系统
 *
 * @author hadymic
 */
@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    /**
     * 系统管理分页&查找管理员
     *
     */
      @RequestMapping("admin/admin/list")
       public  BaseRespVo admin(Page page,Admin admin){
            ListBean<Admin> adminList= adminService.queryAdmin(page,admin.getUsername());
            return  BaseRespVo.success(adminList);
      }

    /**
     * 新增管理员
     * @param admin
     * @return
     */
    @RequestMapping("admin/admin/create")
          public BaseRespVo  insertAdmin(Admin admin){
        List adminList = new ArrayList();
         adminList=adminService.insertAdmin(admin);
          return  BaseRespVo.success(adminList);
        }





    }




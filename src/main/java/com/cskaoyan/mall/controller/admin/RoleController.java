package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.service.role.RoleService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台登录系统
 *
 * @author hadymic
 */
@RestController
public class RoleController {
    @Autowired
 RoleService roleService;


    @RequestMapping("admin/role/list")
    public BaseRespVo role(Page page,Role role){
        ListBean<Role> roles=roleService.queryRole(page,role.getName());
        return  BaseRespVo.success(roles);
    }



}




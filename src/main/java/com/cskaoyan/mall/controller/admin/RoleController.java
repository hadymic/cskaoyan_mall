package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.service.admin.RoleService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台登录系统
 *角色管理
 *
 */
@RestController
public class RoleController {
    @Autowired
 RoleService roleService;


    /**
     * 显示&分页
     * @param page
     * @param role
     * @return
     */
    @RequestMapping("admin/role/list")
    public BaseRespVo role(Page page,Role role){
        ListBean<Role> roles=roleService.queryRole(page,role.getName());
        return  BaseRespVo.success(roles);
    }

    /**
     * optons
     * @return
     */
     @RequestMapping("admin/role/options")
    public  BaseRespVo roleOptions(){
         ListBean<CategoryVo> category = roleService.roleOptions();
         return  BaseRespVo.success(category);
     }


}




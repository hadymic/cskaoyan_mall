package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.service.admin.AdminService;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private LogService logService;

    /**
     * 系统管理分页&查找管理员
     */
    @RequestMapping("admin/admin/list")
    @RequiresPermissions(value = "admin:admin:list")
    public BaseRespVo admin(Page page, Admin admin) {
        ListBean<Admin> adminList = adminService.queryAdmin(page, admin.getUsername());
        return BaseRespVo.success(adminList);
    }

    /**
     * 修改管理员信息
     *
     * @param admin
     * @return
     */
    @RequestMapping("admin/admin/update")
    @RequiresPermissions(value = "admin:admin:update")
    public BaseRespVo update(@RequestBody Admin admin) {
        int flag = adminService.update(admin);
        if (flag == 1) {
            logService.log(1, "修改管理员", true);
            return BaseRespVo.success(admin);
        } else {
            logService.log(1, "修改管理员", false);
            return BaseRespVo.fail("更新失败");
        }
    }


    /**
     * 删除管理员
     *
     * @param admin
     * @return
     */
    @RequestMapping("admin/admin/delete")
    @RequiresPermissions(value = "admin:admin:delete")
    public BaseRespVo delete(@RequestBody Admin admin) {
        adminService.delete(admin);
        logService.log(1, "删除管理员", true);
        return BaseRespVo.success(null);
    }


    /**
     * 新增管理员
     *
     * @param admin
     * @return
     */
    @RequestMapping("admin/admin/create")
    @RequiresPermissions(value = "admin:admin:create")
    public BaseRespVo create(@RequestBody Admin admin) {
        //新增的管理员在数据库中是否存在
        int flag = adminService.queryIsExist(admin.getUsername(), admin.getPassword());
        if (flag == 0) {
            Admin adminMsg = adminService.insertAdmin(admin);
            logService.log(1, "添加管理员", true);
            return BaseRespVo.success(adminMsg);
        } else {
            logService.log(1, "添加管理员", false);
            return BaseRespVo.fail("数据库中已存在该管理员");
        }
    }
}




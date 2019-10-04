package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.service.admin.RoleService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.BaseValueLabel;
import com.cskaoyan.mall.vo.permission.PermissionVo;
import com.cskaoyan.mall.vo.permission.PermissionsVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 后台登录系统
 * 角色管理
 */
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;


    /**
     * 显示&分页
     *
     * @param page
     * @param role
     * @return
     */
    @RequestMapping("admin/role/list")
    @RequiresPermissions(value = "admin:role:list")
    public BaseRespVo role(Page page, Role role) {
        ListBean<Role> roles = roleService.queryRole(page, role.getName());
        return BaseRespVo.success(roles);
    }

    /**
     * 角色管理类别
     *
     * @return
     */
    @RequestMapping("admin/role/options")
    @RequiresPermissions(value = "admin:role:options")
    public BaseRespVo roleOptions() {
        List<BaseValueLabel> category = roleService.roleOptions();
        return BaseRespVo.success(category);
    }


    /**
     * 修改角色管理信息
     *
     * @param role
     * @return
     */
    @RequestMapping("admin/role/update")
    @RequiresPermissions(value = "admin:role:update")
    public BaseRespVo update(@RequestBody Role role) {
        role.setUpdateTime(new Date());
        role.setAddTime(new Date());
        int flag = roleService.update(role);
        if (flag == 1) {
            return BaseRespVo.success(null);
        } else return BaseRespVo.fail("更新失败");
    }


    /**
     * 删除角色管理
     *
     * @param role
     * @return
     */

    @RequestMapping("admin/role/delete")
    @RequiresPermissions(value = "admin:role:delete")
    public BaseRespVo delete(@RequestBody Role role) {

        roleService.delete(role);
        return BaseRespVo.success(null);
    }


    /**
     * 新增角色管理
     *
     * @param role
     * @return
     */
    @RequestMapping("admin/role/create")
    public BaseRespVo create(@RequestBody Role role) {
        Role roleMsg = roleService.insertRole(role);
        return BaseRespVo.success(roleMsg);
    }

    @GetMapping("admin/role/permissions")
    public BaseRespVo rolePermission(int roleId) {
        PermissionVo permissionVo = roleService.rolePermission(roleId);
        return BaseRespVo.success(permissionVo);
    }

    @PostMapping("admin/role/permissions")
    public BaseRespVo updateRolePermission(PermissionsVo vo) {
        boolean flag = roleService.updateRolePermission(vo);
        return flag ? BaseRespVo.success(null) : BaseRespVo.fail("授权失败");
    }
}




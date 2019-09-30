package com.cskaoyan.mall.controller.auth;

<<<<<<< HEAD:src/main/java/com/cskaoyan/mall/controller/admin/AuthController.java
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.service.AuthService;
import com.cskaoyan.mall.util.Page;
=======
>>>>>>> 9f4b65d61e323b0a24ca8309501ea8667d392872:src/main/java/com/cskaoyan/mall/controller/auth/AuthController.java
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 系统管理分页
     *
     */
      @RequestMapping("admin/admin/list")
       public  BaseRespVo showByPage(Page page){
    //          BaseRespVo baseRespVo=  adminService.queryAdminByPage(page.getPage(),page.getLimit());
      }

}

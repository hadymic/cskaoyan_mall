package com.cskaoyan.mall.controller.user;

import com.cskaoyan.mall.service.userserver.UserManageServer;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserManageServer userManageServer;
    @RequestMapping("admin/user")
    public BaseRespVo adminManage(Page utipage, Model model){
        userManageServer.dispaly(utipage);
        return null;
    }
}

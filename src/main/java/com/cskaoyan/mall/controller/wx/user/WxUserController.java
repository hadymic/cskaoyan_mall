package com.cskaoyan.mall.controller.wx.user;

import com.cskaoyan.mall.service.userserver.UserManageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jszza
 */
@RestController
@RequestMapping("wx/user")
public class WxUserController {
    @Autowired
    private UserManageService userManageService;

    @RequestMapping("index")
    public BaseRespVo userIndex(){
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        return BaseRespVo.success(userManageService.queryUserOrders(token));
    }

}

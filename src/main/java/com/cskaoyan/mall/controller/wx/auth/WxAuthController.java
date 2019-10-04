package com.cskaoyan.mall.controller.wx.auth;

import com.cskaoyan.mall.service.auth.AuthService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.auth.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 前台登陆系统
 * @author
 */
@RestController
@RequestMapping("wx/auth")
public class WxAuthController {
    @Autowired
    private AuthService authService;

    /**
     * 后台登录
     *
     * @param vo
     * @return
     */
    @RequestMapping("login")
    public BaseRespVo login(@RequestBody LoginVo vo, HttpServletRequest request) {
        return BaseRespVo.success("r6ycq7oeoo4fnxtkfvyprk94bnx59iec");
    }
}

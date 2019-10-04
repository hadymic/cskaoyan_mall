package com.cskaoyan.mall.controller.auth;

import com.cskaoyan.mall.service.auth.AuthService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.auth.LoginVo;
import com.cskaoyan.mall.vo.auth.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * 后台登录系统
 *
 * @author hadymic
 */
@RestController
@RequestMapping("admin/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 后台登录
     *
     * @param vo
     * @return
     */
    @RequestMapping("login")
    public BaseRespVo login(@RequestBody LoginVo vo) {
        UsernamePasswordToken token = new UsernamePasswordToken(vo.getUsername(), vo.getPassword());
        /*认证的逻辑*/
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return BaseRespVo.fail("登录失败");
        }
        Serializable id = subject.getSession().getId();
        return BaseRespVo.success(id);
    }

    /**
     * 管理员登录信息
     *
     * @param token
     * @return
     */
    @RequestMapping("info")
    public BaseRespVo info(String token) {
        Subject subject = SecurityUtils.getSubject();
        Serializable id = subject.getSession().getId();
        if (!id.equals(token)) {
            throw new AuthorizationException("sessionId错误");
//            return BaseRespVo.fail("管理员信息获取失败");
        }

        String principal = (String) subject.getPrincipal();
        UserInfo userInfo = authService.getAdminInfo(principal);
        return BaseRespVo.success(userInfo);
    }

    /**
     * 后台登录
     *
     * @return
     */
    @RequestMapping("logout")
    public BaseRespVo logout() {
        /*认证的逻辑*/
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.success(null);
    }
}

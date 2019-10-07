package com.cskaoyan.mall.controller.wx.auth;

import com.cskaoyan.mall.service.auth.AuthService;
import com.cskaoyan.mall.shiro.CustomToken;
import com.cskaoyan.mall.util.IPUtils;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.auth.LoginVo;
import com.cskaoyan.mall.vo.wx.auth.UserLoginVo;
import com.cskaoyan.mall.vo.wx.auth.UserRegisterVo;
import com.cskaoyan.mall.vo.wx.auth.UserResetVo;
import com.cskaoyan.mall.vo.wx.auth.regCaptchaVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 微信登录系统
 *
 * @author hadymic
 * Date 2019/7/8 Time 20:55
 */
@RestController
@RequestMapping("wx/auth")
public class WxAuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("login")
    public BaseRespVo login(@RequestBody LoginVo vo, HttpServletRequest request) {
        CustomToken token = new CustomToken(vo.getUsername(), vo.getPassword(), "wx");

        /*认证的逻辑*/
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return BaseRespVo.fail("登录失败");
        }

        String ip = IPUtils.getIpAddr(request);
        //根据username和password查询user信息
        UserLoginVo userLoginVo = authService.wxLogin(vo, ip);

        return BaseRespVo.success(userLoginVo);
    }

    @PostMapping("regCaptcha")
    public BaseRespVo sendValidateCode(@RequestBody regCaptchaVo vo) {
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        String mobile = vo.getMobile();

        boolean flag = authService.sendMessage(mobile, code);

        if (flag) {
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("code", code);
            return BaseRespVo.success(session.getId());
        } else {
            return BaseRespVo.fail("验证码发送失败，请稍后重试！");
        }
    }

    @PostMapping("register")
    public BaseRespVo register(@RequestBody UserRegisterVo vo, HttpServletRequest request) {
        Session session = SecurityUtils.getSubject().getSession();
        String codeFromSession = (String) session.getAttribute("code");
        String code = vo.getCode();
        if (!code.equals(codeFromSession)) {
            return BaseRespVo.fail("验证码错误！");
        }
        String ip = IPUtils.getIpAddr(request);
        Map map = authService.wxRegister(vo, ip);
        if (map.get("userLoginVo") != null) {
            return BaseRespVo.success(map.get("userLoginVo"));
        } else {
            return BaseRespVo.fail((String) map.get("msg"));
        }
    }

    @PostMapping("login_by_weixin")
    public BaseRespVo loginByWeixin(HttpServletRequest request) {
        return BaseRespVo.success(null);
    }

    @RequestMapping("logout")
    public BaseRespVo logout() {
        /*认证的逻辑*/
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.success(null);
    }

    @PostMapping("reset")
    public BaseRespVo reset(@RequestBody UserResetVo vo) {
        Session session = SecurityUtils.getSubject().getSession();
        String codeFromSession = (String) session.getAttribute("code");
        String code = vo.getCode();
        if (!code.equals(codeFromSession)) {
            return BaseRespVo.fail("验证码错误！");
        }

        boolean flag = authService.wxReset(vo);
        return flag ? BaseRespVo.success(null) : BaseRespVo.fail("密码重置失败");
    }
}

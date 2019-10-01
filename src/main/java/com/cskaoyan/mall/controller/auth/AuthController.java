package com.cskaoyan.mall.controller.auth;
import com.cskaoyan.mall.service.auth.AuthService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.LoginVo;
import com.cskaoyan.mall.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

/**
 * 后台登录系统
 *
 * @author hadymic
 */
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    /**
     * 后台登录
     *
     * @param vo
     * @return
     */
    @RequestMapping("admin/auth/login")
    public BaseRespVo login(LoginVo vo) {
        return BaseRespVo.success("9d1c67c7-c489-4729-bde0-2015db5cea02");
    }

    /**
     * 管理员登录信息
     *
     * @param token
     * @return
     */
    @RequestMapping("admin/auth/info")
    public BaseRespVo info(String token) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.setName("admin123");
        List<String> perms = new ArrayList<>();
        perms.add("*");
        userInfo.setPerms(perms);
        List<String> roles = new ArrayList<>();
        roles.add("超级管理员");
        userInfo.setRoles(roles);
        return BaseRespVo.success(userInfo);
    }
}

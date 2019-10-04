package com.cskaoyan.mall.controller.wx.auth;

import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.auth.LoginVo;
import com.cskaoyan.mall.vo.auth.UserInfo;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by little Stone
 * Date 2019/7/8 Time 20:55
 */
@RestController
@RequestMapping("/wx")
public class WxAuthController {

    @RequestMapping("/auth/login")
    @ResponseBody
    public Object login(@RequestBody LoginVo vo, HttpServletRequest request) {
        String username = vo.getUsername();
        String password = vo.getPassword();

        //*******************************
        //根据username和password查询user信息
        //*******************************

        // userInfo
        UserInfo userInfo = new UserInfo();
        //userInfo.setAvatarUrl(user.getAvatar());


        //********************************
        //根据获得userid
        int userId = 1;
        //********************************
        // token
//        UserToken userToken = UserTokenManager.generateToken(userId);

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", "1");
        result.put("tokenExpire", new Date().toString());
        result.put("userInfo", userInfo);
        return BaseRespVo.success(result);
    }

    /*@GetMapping("user/index")
    public Object list(HttpServletRequest request) {
        //前端写了一个token放在请求头中
        //*************************
        //获得请求头
        String tokenKey = request.getHeader("X-cskaoyanmall-Admin-Token");
//        Integer userId = UserTokenManager.getUserId(tokenKey);
        //通过请求头获得userId，进而可以获得一切关于user的信息
        //**************************
        *//*if (userId == null) {
            return BaseRespVo.fail();
        }*//*

        Map<Object, Object> data = new HashMap<Object, Object>();
        //***********************************
        //根据userId查询订单信息
        data.put("order", null);
        //***********************************

        return BaseRespVo.success(data);
    }*/
}

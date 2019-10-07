package com.cskaoyan.mall.service.auth;

import com.cskaoyan.mall.vo.auth.AdminInfo;
import com.cskaoyan.mall.vo.auth.LoginVo;
import com.cskaoyan.mall.vo.wx.auth.UserLoginVo;
import com.cskaoyan.mall.vo.wx.auth.UserRegisterVo;
import com.cskaoyan.mall.vo.wx.auth.UserResetVo;

import java.util.Map;

/**
 * 登录service
 *
 * @author hadymic
 */
public interface AuthService {
    AdminInfo getAdminInfo(String principal);

    boolean sendMessage(String mobile, String code);

    UserLoginVo wxLogin(LoginVo vo, String ip);

    Map wxRegister(UserRegisterVo vo, String ip);

    boolean wxReset(UserResetVo vo);
}

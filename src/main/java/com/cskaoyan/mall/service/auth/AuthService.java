package com.cskaoyan.mall.service.auth;

import com.cskaoyan.mall.vo.auth.UserInfo;

/**
 * 登录service
 *
 * @author hadymic
 */
public interface AuthService {
    UserInfo getAdminInfo(String principal);
}

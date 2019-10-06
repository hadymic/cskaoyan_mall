package com.cskaoyan.mall.vo.wx.auth;

import java.time.LocalDateTime;

/**
 * 微信商城登录返回vo
 */
public class UserLoginVo {
    private String token;
    private LocalDateTime tokenExpire;
    private UserInfo userInfo;

    public UserLoginVo(String token, LocalDateTime tokenExpire, String avatarUrl, String nickName) {
        this.token = token;
        this.tokenExpire = tokenExpire;
        this.userInfo = new UserInfo(avatarUrl, nickName);
    }

    private class UserInfo {
        private String avatarUrl;
        private String nickName;

        public UserInfo(String avatarUrl, String nickName) {
            this.avatarUrl = avatarUrl;
            this.nickName = nickName;
        }

        public String getAvatarUrl() {
            return avatarUrl;
        }

        public void setAvatarUrl(String avatarUrl) {
            this.avatarUrl = avatarUrl;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(LocalDateTime tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}

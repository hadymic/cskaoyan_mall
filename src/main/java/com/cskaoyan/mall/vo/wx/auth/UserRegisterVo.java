package com.cskaoyan.mall.vo.wx.auth;

/**
 * 微信注册vo
 *
 * @author hadymic
 */
public class UserRegisterVo {
    private String code;
    private Integer mobile;
    private String password;
    private String username;
    private String wxCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }
}

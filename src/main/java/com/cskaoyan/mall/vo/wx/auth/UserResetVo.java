package com.cskaoyan.mall.vo.wx.auth;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserResetVo {
    private String code;
    @Pattern(regexp = "^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$", message = "手机号格式不正确")
    private String mobile;
    @Size(max = 12, min = 6, message = "密码长度应6-12位")
    private String password;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

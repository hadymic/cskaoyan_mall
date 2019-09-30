package com.cskaoyan.mall.vo;

import java.util.List;

/**
 * 管理员登录信息
 *
 * @author hadymic
 */
public class UserInfo {
    private String avatar;
    private String name;
    private List<String> perms;
    private List<String> roles;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPerms() {
        return perms;
    }

    public void setPerms(List<String> perms) {
        this.perms = perms;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}

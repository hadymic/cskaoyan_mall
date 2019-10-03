package com.cskaoyan.mall.service.auth.impl;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.service.auth.AuthService;
import com.cskaoyan.mall.vo.auth.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 登录service实现类
 *
 * @author hadymic
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserInfo getAdminInfo(String principal) {
        Admin admin = adminMapper.queryAdminByUsername(principal);

        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar(admin.getAvatar());
        userInfo.setName(admin.getUsername());

        List<String> permissions = adminMapper.queryPermissionsByUsername(principal);
        if (!(permissions.size() == 1 && "*".equals(permissions.get(0)))) {
            List<String> truePermissions = new ArrayList<>();
            for (String permission : permissions) {
                truePermissions.add("/" + permission.replace(":", "/"));
            }
            userInfo.setPerms(truePermissions);
        } else {
            userInfo.setPerms(permissions);
        }

        List<String> roleNames = roleMapper.queryRoleNameByRoleIds(admin.getRoleIds());

        userInfo.setRoles(roleNames);
        return userInfo;
    }
}

package com.cskaoyan.mall.service.auth.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.config.AliyunConfig;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.auth.AuthService;
import com.cskaoyan.mall.util.IPUtils;
import com.cskaoyan.mall.vo.auth.AdminInfo;
import com.cskaoyan.mall.vo.auth.LoginVo;
import com.cskaoyan.mall.vo.wx.auth.UserLoginVo;
import com.cskaoyan.mall.vo.wx.auth.UserRegisterVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AliyunConfig aliyunConfig;

    @Override
    public AdminInfo getAdminInfo(String principal) {
        Admin admin = adminMapper.queryAdminByUsername(principal);

        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAvatar(admin.getAvatar());
        adminInfo.setName(admin.getUsername());

        List<String> permissions = adminMapper.queryApisByUsername(principal);
        if (permissions.size() == 1 && permissions.get(0) == null) {
            List<String> truePermissions = new ArrayList<>();
            truePermissions.add("*");
            adminInfo.setPerms(truePermissions);
        } else {
            adminInfo.setPerms(permissions);
        }

        List<String> roleNames = roleMapper.queryRoleNameByRoleIds(admin.getRoleIds());

        adminInfo.setRoles(roleNames);
        return adminInfo;
    }

    @Override
    public boolean sendMessage(String mobile, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                aliyunConfig.getAccessKeyId(), aliyunConfig.getAccessSecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", aliyunConfig.getSmsConfig().getRegionId());
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", aliyunConfig.getSmsConfig().getSignName());
        request.putQueryParameter("TemplateCode", aliyunConfig.getSmsConfig().getTemplateCode());
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            ObjectMapper objectMapper = new ObjectMapper();
            Map map = objectMapper.readValue(response.getData(), Map.class);
            String message = (String) map.get("Message");
            return "OK".equals(message);
        } catch (ServerException e) {
            return false;
            //e.printStackTrace();
        } catch (ClientException e) {
            return false;
            //e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public UserLoginVo wxLogin(LoginVo vo, String ip) {
        User user = userMapper.queryByUserNameAndPassword(vo.getUsername(), vo.getPassword());
        return new UserLoginVo(SecurityUtils.getSubject().getSession().getId().toString(), LocalDateTime.now().plusDays(1), user.getAvatar(), user.getNickname());
    }

    @Override
    public UserLoginVo wxRegister(UserRegisterVo vo, String ip) {
        /*Date date = new Date();
        User user = new User();
        user.setUsername(vo.getUsername());
        user.setPassword(vo.getPassword());
        user.setGender((byte) 0);
        user.setLastLoginTime(date);
        user.setLastLoginIp(ip);
        user.setAddTime(date);
        user.setNickname(vo.getUsername());
        user.setDeleted(false);
        userMapper.insert(user);*/

        User user = userMapper.queryByUserNameAndPassword(vo.getUsername(), vo.getPassword());
        new UserLoginVo(SecurityUtils.getSubject().getSession().getId().toString(), LocalDateTime.now().plusDays(1), user.getAvatar(), user.getNickname());
        return null;
    }
}

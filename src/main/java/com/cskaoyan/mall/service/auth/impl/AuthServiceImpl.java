package com.cskaoyan.mall.service.auth.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.config.AliyunConfig;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.auth.AuthService;
import com.cskaoyan.mall.vo.auth.AdminInfo;
import com.cskaoyan.mall.vo.auth.LoginVo;
import com.cskaoyan.mall.vo.wx.auth.UserLoginVo;
import com.cskaoyan.mall.vo.wx.auth.UserRegisterVo;
import com.cskaoyan.mall.vo.wx.auth.UserResetVo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.rmi.ServerException;
import java.time.LocalDateTime;
import java.util.*;

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
    private CouponUserMapper couponUserMapper;

    @Autowired
    private CouponMapper couponMapper;

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
        request.putQueryParameter("TemplateParam", "{\"code\":\"" + code + "\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
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

        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setLastLoginTime(new Date());
        updateUser.setLastLoginIp(ip);
        userMapper.updateByPrimaryKeySelective(updateUser);

        SecurityUtils.getSubject().getSession().setAttribute("userId", user.getId());
        return new UserLoginVo(SecurityUtils.getSubject().getSession().getId().toString(),
                LocalDateTime.now().plusDays(1), user.getAvatar(), user.getNickname());
    }

    @Override
    public Map wxRegister(UserRegisterVo vo, String ip) {
        Map map = new HashMap(1);
        //校验同名
        int count = userMapper.queryCountByToken(vo.getUsername(), null);
        if (count >= 1) {
            map.put("msg", "用户名已存在，请更换用户名！");
            return map;
        }
        //校验手机号
        count = userMapper.queryCountByToken(null, vo.getMobile());
        if (count >= 1) {
            map.put("msg", "手机号已存在，请前往登录");
            return map;
        }
        //插入数据库
        Date date = new Date();
        String avatar = "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif";
        User user = new User(null, vo.getUsername(), vo.getPassword(), (byte) 0,
                null, date, ip, (byte) 0, vo.getUsername(), vo.getMobile(),
                avatar, null, (byte) 0, date, date, false);
        int flag = userMapper.insertSelective(user);
        if (flag == 1) {
            Coupon coupon = couponMapper.selectByPrimaryKey(3);
            CouponUser couponUser = new CouponUser();
            couponUser.setCouponId(3);
            couponUser.setUserId(user.getId());
            couponUser.setStatus((short) 0);
            couponUser.setAddTime(date);
            couponUser.setStartTime(date);
            Date date1 = new Date();
            Calendar cl = Calendar.getInstance();
            cl.setTime(date1);
            cl.add(Calendar.DATE, 5);
            Date time = cl.getTime();
            couponUser.setEndTime(time);
            couponUser.setDeleted(false);
            couponUserMapper.insertSelective(couponUser);
            SecurityUtils.getSubject().getSession().setAttribute("userId", user.getId());

            UserLoginVo userLoginVo = new UserLoginVo(SecurityUtils.getSubject().getSession().getId().toString(),
                    LocalDateTime.now().plusDays(1), user.getAvatar(), user.getNickname());
            map.put("userLoginVo", userLoginVo);
        }
        map.put("msg", "系统错误，注册失败，请稍后重试");
        return map;
    }

    @Override
    public boolean wxReset(UserResetVo vo) {
        return userMapper.updatePasswordByMobile(vo.getPassword(), vo.getMobile()) == 1;
    }
}

package com.cskaoyan.mall.service.auth.impl;

import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.auth.HomeService;
import com.cskaoyan.mall.util.StringUtils;
import com.cskaoyan.mall.vo.ChangePasswordVo;
import com.cskaoyan.mall.vo.HomeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jszza
 */
@Service
public class HomeServiceImpl implements HomeService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    AdminMapper adminMapper;

    @Override
    public HomeVo queryHomeInfo() {
        HomeVo homeVo = new HomeVo();
        homeVo.setGoodsTotal(goodsMapper.queryGoodsNumber());
        homeVo.setOrderTotal(orderMapper.queryOrderNumber());
        homeVo.setProductTotal(goodsProductMapper.queryGoodsProductNumber());
        homeVo.setUserTotal(userMapper.queryUserNumber());
        return homeVo;
    }

    @Override
    public String updatePassword(ChangePasswordVo changePasswordVo, String token) {
        if (!adminMapper.queryPasswordByUsername(token).equals(changePasswordVo.getOldPassword())) {
            return "原密码错误";
        }
        if (StringUtils.isEmpty(changePasswordVo.getNewPassword()) || StringUtils.isEmpty(changePasswordVo.getNewPassword2())) {
            return "新密码不能为空";
        }
        if (!changePasswordVo.getNewPassword().equals(changePasswordVo.getNewPassword2())
                || changePasswordVo.getNewPassword().equals(changePasswordVo.getOldPassword())) {
            return "新旧密码不能相同";
        }
        if (changePasswordVo.getNewPassword().length() < 6 || !changePasswordVo.getNewPassword().matches(".*[a-zA-Z]+.*")) {
            return "输入的密码不能过短或者不包含字母";
        }
        int i = adminMapper.updatePassword(changePasswordVo.getOldPassword(), changePasswordVo.getNewPassword(), token);
        return i == 0 ? "修改失败" : null;
    }
}

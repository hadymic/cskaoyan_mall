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
    public boolean updatePassword(ChangePasswordVo changePasswordVo, String token) {
        if (StringUtils.isEmpty(changePasswordVo.getNewPassword()) || StringUtils.isEmpty(changePasswordVo.getNewPassword2())){
            return false;
        }
        if (!changePasswordVo.getNewPassword().equals(changePasswordVo.getNewPassword2())){
            return false;
        }
        if (changePasswordVo.getNewPassword().length() < 10 || changePasswordVo.getNewPassword().matches(".*[a-zA-Z]+.*")){
            return false;
        }
        int i = adminMapper.updatePassword(changePasswordVo.getOldPassword(),changePasswordVo.getNewPassword(),token);
        return i != 0;
    }
}

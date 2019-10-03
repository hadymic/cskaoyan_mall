package com.cskaoyan.mall.service.auth.impl;

import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GoodsProductMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.auth.HomeService;
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

    @Override
    public HomeVo queryHomeInfo() {
        HomeVo homeVo = new HomeVo();
        homeVo.setGoodsTotal(goodsMapper.queryGoodsNumber());
        homeVo.setOrderTotal(orderMapper.queryOrderNumber());
        homeVo.setProductTotal(goodsProductMapper.queryGoodsProductNumber());
        homeVo.setUserTotal(userMapper.queryUserNumber());
        return homeVo;
    }
}

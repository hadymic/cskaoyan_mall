package com.cskaoyan.mall.service.wx.goods.impl;

import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.service.wx.goods.WxGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxGoodsServiceImpl implements WxGoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public int count() {
        return goodsMapper.queryGoodsNumber();
    }
}

package com.cskaoyan.mall.service.goods.impl;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public ListBean queryGoods(Page page) {
        List<Goods> goodsList = goodsMapper.selectGoodsList();
        ListBean<Goods> listBean = PageUtils.page(page, goodsList);
        return listBean;
    }
}

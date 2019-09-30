package com.cskaoyan.mall.service.goods.impl;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public ListBean queryGoods(Page page) {
        com.github.pagehelper.Page<Object> startPage = PageHelper.startPage(page.getPage(), page.getLimit());
        List<Goods> goodsList = goodsMapper.selectGoodsList(page);
        int total = (int) startPage.getTotal();
        ListBean listBean = new ListBean();
        listBean.setItems(goodsList);
        listBean.setTotal(total);
        return listBean;
    }
}

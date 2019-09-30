package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @RequestMapping("goods/list")
    public ListBean GoodsList(Page page) {
        ListBean listBean = goodsService.queryGoods(page);
        return listBean;
    }
}

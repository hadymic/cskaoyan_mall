package com.cskaoyan.mall.controller.goods;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("admin")
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @RequestMapping("goods/list")
    public BaseRespVo GoodsList(Page page){
        ListBean listBean = goodsService.queryGoods(page);
        return BaseRespVo.success(listBean);
    }
}

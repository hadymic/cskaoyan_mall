package com.cskaoyan.mall.controller.goods;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.goodsMangement.BaseValueLabel;
import com.cskaoyan.mall.vo.goodsMangement.CategoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @RequestMapping("admin/goods/list")
    public BaseRespVo GoodsList(Page page, Goods goods) {
        if (goods != null) {
            ListBean listBean = goodsService.selectGoodsByGoodsSnOrName(page, goods);
            return BaseRespVo.success(listBean);
        }
        ListBean listBean = goodsService.queryGoods(page);
        return BaseRespVo.success(listBean);
    }

    @RequestMapping("admin/goods/catAndBrand")
    public BaseRespVo CatAndBrand() {
        List<BaseValueLabel> brandList = goodsService.selectBrandList();
        List<CategoryList> categoryList = goodsService.selectCategory();
        Map map = new HashMap<>();
        map.put("categoryList", categoryList);
        map.put("brandList", brandList);
        return BaseRespVo.success(map);
    }

    @RequestMapping("admin/goods/delete")
    public BaseRespVo deleteGoods(Goods goods) {
        goodsService.deleteGoods(goods);
        return BaseRespVo.success(null);
    }

    @RequestMapping("admin/goods/detail")
    public BaseRespVo GoodsDetail(int id) {
        Goods goods = goodsService.selectGoodsDetail(id);
        return BaseRespVo.success(goods);
    }
}

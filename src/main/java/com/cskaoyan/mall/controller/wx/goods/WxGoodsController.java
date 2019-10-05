package com.cskaoyan.mall.controller.wx.goods;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.service.wx.goods.WxGoodsService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.goodsmanagement.GoodsByCategory;
import com.cskaoyan.mall.vo.wx.goodsmanagement.WxGoodsDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {
    @Autowired
    WxGoodsService wxGoodsService;

    @RequestMapping("category")
    public BaseRespVo GoodsByCategory(int id) {
        Map<String, Object> map = wxGoodsService.showGoodsByCategory(id);
        return BaseRespVo.success(map);
    }

    @RequestMapping("list")
    public BaseRespVo GoodsList(Page page,int categoryId){//商品分页
       GoodsByCategory goodsByCategory = wxGoodsService.PageGoodsByCategory(page,categoryId);
        return BaseRespVo.success(goodsByCategory);
    }

    @RequestMapping("detail")
    public BaseRespVo GoodsDetail(int id){//显示单个商品详情
       WxGoodsDetailVo goodsDetailVo = wxGoodsService.selectWxGoodsDatail(id);
        return BaseRespVo.success(goodsDetailVo);
    }

    @RequestMapping("count")
    public BaseRespVo count(){
        int count = wxGoodsService.count();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("goodsCount", count);
        return BaseRespVo.success(map);
    }
}

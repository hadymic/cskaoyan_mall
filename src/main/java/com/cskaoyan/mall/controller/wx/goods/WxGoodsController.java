package com.cskaoyan.mall.controller.wx.goods;

import com.cskaoyan.mall.service.wx.goods.WxGoodsService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.goodsmanagement.WxGoodsDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
    public BaseRespVo GoodsList(Page page){//分页
        return BaseRespVo.success(null);
    }

    @RequestMapping("detail")
    public BaseRespVo GoodsDetail(int id){
       WxGoodsDetailVo goodsDetailVo = wxGoodsService.selectWxGoodsDatail(id);
        return BaseRespVo.success(goodsDetailVo);
    }

}

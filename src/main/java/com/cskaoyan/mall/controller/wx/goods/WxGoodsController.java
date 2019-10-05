package com.cskaoyan.mall.controller.wx.goods;

import com.cskaoyan.mall.service.wx.goods.WxGoodsService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {

    @Autowired
    WxGoodsService wxGoodsService;

    @RequestMapping("count")
    public BaseRespVo count(){
        int count = wxGoodsService.count();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("goodsCount", count);
        return BaseRespVo.success(map);
    }
}

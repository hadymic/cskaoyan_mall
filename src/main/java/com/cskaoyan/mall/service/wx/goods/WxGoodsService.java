package com.cskaoyan.mall.service.wx.goods;

import com.cskaoyan.mall.vo.wx.goodsmanagement.WxGoodsDetailVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface WxGoodsService {
    Map<String, Object> showGoodsByCategory(int id);

    WxGoodsDetailVo selectWxGoodsDatail(int id);
}

package com.cskaoyan.mall.service.wx.goods;


import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.wx.goodsmanagement.GoodsByCategory;
import com.cskaoyan.mall.vo.wx.goodsmanagement.WxGoodsDetailVo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface WxGoodsService {
    Map<String, Object> showGoodsByCategory(int id);

    WxGoodsDetailVo selectWxGoodsDatail(int id);

    int count();

    /**
     * @author hjl 商品按种类显示
     * @param page
     * @param categoryId
     * @return
     */
    GoodsByCategory PageGoodsByCategory(Page page, int categoryId);
}

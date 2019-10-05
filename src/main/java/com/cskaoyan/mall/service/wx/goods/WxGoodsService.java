package com.cskaoyan.mall.service.wx.goods;


import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.wx.goodsmanagement.GoodsByCategory;
import com.cskaoyan.mall.vo.wx.goodsmanagement.WxGoodsDetailVo;

import java.util.List;
import java.util.Map;

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

    List<Goods> showRelatedGoods(int id);

    /**
     * @author hjl 商品按商标显示
     * @param page
     * @param brandId
     * @return
     */
    GoodsByCategory PageGoodsByBrand(Page page, int brandId);
}

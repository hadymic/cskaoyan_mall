package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.goodsMangement.BaseValueLabel;
import com.cskaoyan.mall.vo.goodsMangement.CategoryList;

import java.util.List;


/**
 * @author stark_h
 */
public interface GoodsService {
    ListBean queryGoods(Page page);

    //根据goodsSn(精确查找) 或 name(模糊查找)
    ListBean selectGoodsByGoodsSnOrName(Page page, Goods goods);

    List<BaseValueLabel> selectBrandList();

    //添加商品中CategoryList
    List<CategoryList> selectCategory();

    //删除商品(将deleted置为1)
    void deleteGoods(Goods goods);

    Goods selectGoodsDetail(int id);
}

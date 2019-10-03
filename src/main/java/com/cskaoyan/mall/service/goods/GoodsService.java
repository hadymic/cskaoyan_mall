package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseValueLabel;
import com.cskaoyan.mall.vo.goodsmanagement.CategoryList;
import com.cskaoyan.mall.vo.goodsmanagement.GoodsEditVo;

import java.util.List;


/**
 * @author stark_h
 */
public interface GoodsService {
    //不为空的话，根据goodsSn(精确查找) 或 name(模糊查找)
    ListBean selectGoods(Page page, Goods goods);

    List<BaseValueLabel> selectBrandList();

    //添加商品中CategoryList
    List<CategoryList> selectCategory();

    //删除商品(将deleted置为1)
    void deleteGoods(Goods goods);

    GoodsEditVo selectGoodsDetail(int id);
//商品编辑，更新goods，goodsAttribute，goodsProduct，goodsSpecification,category
    boolean updateGoods(GoodsEditVo goodsEditVo);
//添加商品
    boolean createGoods(GoodsEditVo goodsEditVo);
}

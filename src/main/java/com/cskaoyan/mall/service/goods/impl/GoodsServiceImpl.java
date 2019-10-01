package com.cskaoyan.mall.service.goods.impl;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.goodsMangement.BaseValueLabel;
import com.cskaoyan.mall.vo.goodsMangement.CategoryList;
import com.cskaoyan.mall.vo.goodsMangement.GoodsEditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


/**
 * @author stark_h
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Override
    public ListBean queryGoods(Page page) {
        PageUtils.startPage(page);
        List<Goods> goodsList = goodsMapper.selectGoodsList();
        return PageUtils.page(goodsList);
    }

    @Override
    public ListBean selectGoodsByGoodsSnOrName(Page page, Goods goods) {
        PageUtils.startPage(page);
        List<Goods> goodsList = goodsMapper.selectGoodsByGoodsSnOrName(goods);
        return PageUtils.page(goodsList);
    }

    @Override
    public List<BaseValueLabel> selectBrandList() {
        List<BaseValueLabel> brandList = brandMapper.selectBrandList();
        return brandList;
    }

    @Override
    public List<CategoryList> selectCategory() {
        List<BaseValueLabel> list = categoryMapper.selectCategory(0);
        List<CategoryList> categoryLists = new ArrayList<>(list.size());
        for (BaseValueLabel baseValueLabel : list) {
            /*CategoryList categoryList = new CategoryList();
            categoryList.setValue(baseValueLabel.getValue());
            categoryList.setLabel(baseValueLabel.getLabel());
            categoryList.setChildren(categoryMapper.selectCategory(baseValueLabel.getValue()));
            categoryLists.add(categoryList);*/
            categoryLists.add(new CategoryList(baseValueLabel.getValue(),baseValueLabel.getLabel(),categoryMapper.selectCategory(baseValueLabel.getValue())));
        }
        return categoryLists;
    }

    @Override
    public void deleteGoods(Goods goods) {
        goodsMapper.deleteGoods(goods.getId());
    }

    @Override
    public GoodsEditVo selectGoodsDetail(int id) {
        Integer categoryId = goodsMapper.selectByPrimaryKey(id).getCategoryId();
        Integer pid = categoryMapper.selectByPrimaryKey(categoryId).getPid();
        int[] categoryIds ={pid,categoryId};
        return new GoodsEditVo(categoryIds,goodsMapper.selectByPrimaryKey(id),goodsAttributeMapper.selectAttributesByGoodsId(id),
                goodsSpecificationMapper.selectSpecificationsByGoodsId(id),goodsProductMapper.selectProductsByGoodsId(id));
    }
}

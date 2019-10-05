package com.cskaoyan.mall.vo.wx.goodsmanagement;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;

import java.util.List;

public class GoodsByCategory {
    private List<Goods> goodsList;

    private  Integer count;

    private  List<Category> filterCategoryList;

    public GoodsByCategory() {
    }

    public GoodsByCategory(List<Goods> goodsList, Integer count, List<Category> filterCategoryList) {
        this.goodsList = goodsList;
        this.count = count;
        this.filterCategoryList = filterCategoryList;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Category> getFilterCategoryList() {
        return filterCategoryList;
    }

    public void setFilterCategoryList(List<Category> filterCategoryList) {
        this.filterCategoryList = filterCategoryList;
    }
}

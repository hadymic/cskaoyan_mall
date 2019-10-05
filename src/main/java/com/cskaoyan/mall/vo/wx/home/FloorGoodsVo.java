package com.cskaoyan.mall.vo.wx.home;

import com.cskaoyan.mall.bean.Goods;

import java.util.List;

public class FloorGoodsVo {

    private int id;
    private String name;
    private List<Goods> goodsList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}

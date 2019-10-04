package com.cskaoyan.mall.vo.wx.home;

import com.cskaoyan.mall.bean.Goods;

import java.util.List;

public class GrouponVo {

    private int goods_id;
    private int groupon_menber;
    private double groupon_price;
    private Goods goods;

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getGroupon_menber() {
        return groupon_menber;
    }

    public void setGroupon_menber(int groupon_menber) {
        this.groupon_menber = groupon_menber;
    }

    public double getGroupon_price() {
        return groupon_price;
    }

    public void setGroupon_price(double groupon_price) {
        this.groupon_price = groupon_price;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}

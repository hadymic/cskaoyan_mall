package com.cskaoyan.mall.vo.wx.cart;

/**
 * 购物车数量
 *
 * @author hadymic
 */
public class CartTotal {
    private int checkedGoodsAmount;
    private int checkedGoodsCount;
    private int goodsAmount;
    private int goodsCount;

    public int getCheckedGoodsAmount() {
        return checkedGoodsAmount;
    }

    public void setCheckedGoodsAmount(int checkedGoodsAmount) {
        this.checkedGoodsAmount = checkedGoodsAmount;
    }

    public int getCheckedGoodsCount() {
        return checkedGoodsCount;
    }

    public void setCheckedGoodsCount(int checkedGoodsCount) {
        this.checkedGoodsCount = checkedGoodsCount;
    }

    public int getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(int goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }
}

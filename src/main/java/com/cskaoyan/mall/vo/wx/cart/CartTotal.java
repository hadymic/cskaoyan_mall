package com.cskaoyan.mall.vo.wx.cart;

import java.math.BigDecimal;

/**
 * 购物车数量
 *
 * @author hadymic
 */
public class CartTotal {
    private BigDecimal checkedGoodsAmount;
    private BigDecimal checkedGoodsCount;
    private BigDecimal goodsAmount;
    private BigDecimal goodsCount;

    public CartTotal() {
    }

    public CartTotal(BigDecimal checkedGoodsAmount, BigDecimal checkedGoodsCount, BigDecimal goodsAmount, BigDecimal goodsCount) {
        this.checkedGoodsAmount = checkedGoodsAmount;
        this.checkedGoodsCount = checkedGoodsCount;
        this.goodsAmount = goodsAmount;
        this.goodsCount = goodsCount;
    }

    public BigDecimal getCheckedGoodsAmount() {
        return checkedGoodsAmount;
    }

    public void setCheckedGoodsAmount(BigDecimal checkedGoodsAmount) {
        this.checkedGoodsAmount = checkedGoodsAmount;
    }

    public BigDecimal getCheckedGoodsCount() {
        return checkedGoodsCount;
    }

    public void setCheckedGoodsCount(BigDecimal checkedGoodsCount) {
        this.checkedGoodsCount = checkedGoodsCount;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public BigDecimal getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(BigDecimal goodsCount) {
        this.goodsCount = goodsCount;
    }
}

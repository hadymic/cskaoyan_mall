package com.cskaoyan.mall.vo.wx.cart;

/**
 * 加入购物车vo
 *
 * @author hadymic
 */
public class CartAddVo {
    private int goodsId;
    private short number;
    private int productId;

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public short getNumber() {
        return number;
    }

    public void setNumber(short number) {
        this.number = number;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}

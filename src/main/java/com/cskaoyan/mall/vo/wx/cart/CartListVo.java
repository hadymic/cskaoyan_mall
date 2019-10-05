package com.cskaoyan.mall.vo.wx.cart;

import com.cskaoyan.mall.bean.Cart;

import java.util.List;

/**
 * 购物车主页返回vo
 *
 * @author hadymic
 */
public class CartListVo {
    private List<Cart> cartList;
    private CartTotal cartTotal;

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public CartTotal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(CartTotal cartTotal) {
        this.cartTotal = cartTotal;
    }
}

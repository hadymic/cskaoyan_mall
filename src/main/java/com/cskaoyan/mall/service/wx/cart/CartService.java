package com.cskaoyan.mall.service.wx.cart;

import com.cskaoyan.mall.vo.wx.cart.CartAddVo;
import com.cskaoyan.mall.vo.wx.cart.CartCheckedVo;
import com.cskaoyan.mall.vo.wx.cart.CartListVo;

import java.math.BigDecimal;

public interface CartService {
    CartListVo cartList(int id);

    String addCart(CartAddVo vo, int userId);

    boolean checkedCart(int userId, CartCheckedVo vo);

    BigDecimal goodsCount(int userId);

    int fastAdd(CartAddVo vo, int userId);
}

package com.cskaoyan.mall.service.wx.cart;

import com.cskaoyan.mall.vo.wx.cart.CartAddVo;
import com.cskaoyan.mall.vo.wx.cart.CartCheckedVo;
import com.cskaoyan.mall.vo.wx.cart.CartListVo;

import java.math.BigDecimal;
import java.util.Map;

public interface CartService {
    CartListVo cartList(int id);

    Map<String, Object> addCart(CartAddVo vo, int userId);

    boolean checkedCart(int userId, CartCheckedVo vo);
}

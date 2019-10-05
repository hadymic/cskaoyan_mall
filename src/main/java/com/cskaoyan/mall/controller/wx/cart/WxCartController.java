package com.cskaoyan.mall.controller.wx.cart;

import com.cskaoyan.mall.service.wx.cart.CartService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.cart.CartListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信购物车
 *
 * @author hadymic
 */
@RestController
@RequestMapping("wx/cart")
public class WxCartController {
    @Autowired
    private CartService cartService;

    @GetMapping("index")
    public BaseRespVo cartList() {
        int id = 1;
        CartListVo cartListVo = cartService.cartList(id);
        return BaseRespVo.success(null);
    }
}

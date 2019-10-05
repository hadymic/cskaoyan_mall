package com.cskaoyan.mall.controller.wx.cart;

import com.cskaoyan.mall.service.wx.cart.CartService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.cart.CartAddVo;
import com.cskaoyan.mall.vo.wx.cart.CartCheckedVo;
import com.cskaoyan.mall.vo.wx.cart.CartListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

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

    /**
     * 购物车主页
     *
     * @return
     */
    @GetMapping("index")
    public BaseRespVo cartList() {
        int userId = 1;
        CartListVo cartListVo = cartService.cartList(userId);
        return BaseRespVo.success(cartListVo);
    }

    /**
     * 添加购物车
     *
     * @param vo
     * @return
     */
    @PostMapping("add")
    public BaseRespVo addCart(@RequestBody CartAddVo vo) {
        int userId = 1;
        String data = cartService.addCart(vo, userId);
        if (data == null) {
            BigDecimal count = cartService.goodsCount(userId);
            return BaseRespVo.success(count);
        }
        return BaseRespVo.fail(data);
    }

    /**
     * 勾选购物车
     *
     * @param vo
     * @return
     */
    @PostMapping("checked")
    public BaseRespVo checkedCart(@RequestBody CartCheckedVo vo) {
        int userId = 1;
        cartService.checkedCart(userId, vo);
        CartListVo cartListVo = cartService.cartList(userId);
        return BaseRespVo.success(cartListVo);
    }

    /**
     * 查询购物车商品数
     *
     * @return
     */
    @GetMapping("goodscount")
    public BaseRespVo goodsCount() {
        int userId = 1;
        BigDecimal data = cartService.goodsCount(userId);
        return BaseRespVo.success(data);
    }

    /**
     * 快速添加购物车
     *
     * @param vo
     * @return
     */
    @PostMapping("fastadd")
    public BaseRespVo fastAdd(@RequestBody CartAddVo vo) {
        int userId = 1;
        int cartId = cartService.fastAdd(vo, userId);
        return BaseRespVo.success(cartId);
    }
}

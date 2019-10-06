package com.cskaoyan.mall.controller.wx.cart;

import com.cskaoyan.mall.service.wx.cart.CartService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.cart.*;
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
        if (cartId == -1) {
            return BaseRespVo.fail("商品太抢手啦，库存已空哦！");
        }
        return BaseRespVo.success(cartId);
    }

    /**
     * checkout
     *
     * @param vo
     * @return
     */
    @GetMapping("checkout")
    public BaseRespVo checkout(CartCheckoutVo vo) {
        CartCheckoutReturnVo returnVo = cartService.checkout(vo);
        return BaseRespVo.success(returnVo);
    }

    /**
     * 编辑购物车
     *
     * @param vo
     * @return
     */
    @PostMapping("update")
    public BaseRespVo updateCart(@RequestBody CartUpdateVo vo) {
        boolean flag = cartService.updateCart(vo);
        return flag ? BaseRespVo.success(null) : BaseRespVo.fail("编辑出错啦，请稍后重试！");
    }

    /**
     * 删除购物车
     *
     * @param vo
     * @return
     */
    @PostMapping("delete")
    public BaseRespVo deleteCart(@RequestBody CartDeleteVo vo) {
        int userId = 1;
        boolean flag = cartService.deleteCart(vo, userId);
        if (flag) {
            CartListVo cartListVo = cartService.cartList(userId);
            return BaseRespVo.success(cartListVo);
        } else {
            return BaseRespVo.fail("删除出错啦，请稍后重试！");
        }
    }
}

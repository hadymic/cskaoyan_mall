package com.cskaoyan.mall.controller.wx.cart;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.service.wx.cart.CartService;
import com.cskaoyan.mall.service.wx.coupon.WxCouponService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.cart.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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
    @Autowired
    private WxCouponService wxCouponService;

    /**
     * 购物车主页
     *
     * @return
     */
    @GetMapping("index")
    public BaseRespVo cartList() {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
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
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
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
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
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
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
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
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        int cartId = cartService.fastAdd(vo, userId);
        if (cartId == -1) {
            return BaseRespVo.fail("商品太抢手啦！库存已空哦！");
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
        List<Coupon> coupons = wxCouponService.couponCanUse(vo.getCartId(), vo.getGrouponRulesId());
        if ((vo.getCouponId() == 0 || vo.getCouponId() == -1) && coupons.size() > 0) {
            returnVo.setAvailableCouponLength(coupons.size());
            returnVo.setCouponId(-1);
        }
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
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        boolean flag = cartService.deleteCart(vo, userId);
        if (flag) {
            CartListVo cartListVo = cartService.cartList(userId);
            return BaseRespVo.success(cartListVo);
        } else {
            return BaseRespVo.fail("删除出错啦，请稍后重试！");
        }
    }
}

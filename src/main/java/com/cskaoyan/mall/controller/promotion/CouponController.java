package com.cskaoyan.mall.controller.promotion;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.service.promotion.CouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 优惠券管理
 *
 * @author hadymic
 */
@RestController
public class CouponController {
    @Autowired
    private CouponService couponService;

    @RequestMapping("admin/coupon/list")
    public BaseRespVo showCouponList(Page page) {
        ListBean<Coupon> couponListBean = couponService.queryAllCoupons(page);
        return BaseRespVo.success(couponListBean);
    }
}

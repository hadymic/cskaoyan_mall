package com.cskaoyan.mall.controller.promotion;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.service.promotion.CouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 优惠券管理
 *
 * @author hadymic
 */
@RestController
@RequestMapping("admin/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    /**
     * 显示优惠券列表
     *
     * @param page
     * @return
     */
    @GetMapping("list")
    public BaseRespVo showCouponList(Page page) {
        ListBean<Coupon> couponListBean = couponService.queryAllCoupons(page);
        return BaseRespVo.success(couponListBean);
    }

    /**
     * 优惠券详情
     *
     * @param id
     * @return
     */
    @GetMapping("read")
    public BaseRespVo couponInfo(int id) {
        Coupon coupon = couponService.queryCouponById(id);
        return BaseRespVo.success(coupon);
    }

    /**
     * 优惠券与用户信息
     *
     * @param page
     * @param couponId
     * @return
     */
    @GetMapping("listuser")
    public BaseRespVo queryCouponUser(Page page, int couponId) {
        ListBean<CouponUser> couponListBean = couponService.queryCouponUser(page, couponId);
        return BaseRespVo.success(couponListBean);
    }
}

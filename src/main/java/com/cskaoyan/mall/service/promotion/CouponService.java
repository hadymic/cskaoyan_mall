package com.cskaoyan.mall.service.promotion;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

/**
 * 优惠券管理Service
 *
 * @author hadymic
 */
public interface CouponService {
    /**
     * 分页查询所有优惠券
     *
     * @param page
     * @return
     */
    ListBean<Coupon> queryAllCoupons(Page page);

    /**
     * 通过优惠券id查询优惠券详情
     *
     * @param id
     * @return
     */
    Coupon queryCouponById(int id);

    /**
     * 通过优惠券id分页查询优惠券与用户的信息
     *
     * @param page
     * @param couponId
     * @return
     */
    ListBean<CouponUser> queryCouponUser(Page page, int couponId);
}

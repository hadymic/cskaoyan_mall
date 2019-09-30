package com.cskaoyan.mall.service.promotion;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

/**
 * 优惠券管理Service
 *
 * @author hadymic
 */
public interface CouponService {
    ListBean<Coupon> queryAllCoupons(Page page);
}

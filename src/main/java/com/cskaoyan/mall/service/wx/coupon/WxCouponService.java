package com.cskaoyan.mall.service.wx.coupon;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.WxListBean;

public interface WxCouponService {
    WxListBean<Coupon> showMyList(Page page, Coupon coupon);

    WxListBean<Coupon> showList(Page page);

    int receiveCoupon(Integer couponId);

    Coupon exchangeCode(String code);

    int isExistCoupon(String code);

    void insertUser(CouponUser couponId);

    void insertDb(Coupon couponCanUse);
}

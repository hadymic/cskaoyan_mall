package com.cskaoyan.mall.service.wx.coupon;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.wx.coupon.CouponVo;

import java.util.List;

public interface WxCouponService {
CouponVo showMyList(Page page, Coupon coupon);

CouponVo showList(Page page);

    int receiveCoupon(Integer couponId);
}

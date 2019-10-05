package com.cskaoyan.mall.service.wx.coupon;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

public interface WxCouponService {
    ListBean<Coupon> showList(Page page, Coupon coupon);
}

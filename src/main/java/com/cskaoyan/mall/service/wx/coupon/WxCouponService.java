package com.cskaoyan.mall.service.wx.coupon;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.WxListBean;
import com.cskaoyan.mall.vo.wx.coupon.WxCouponVo;

import java.util.List;

public interface WxCouponService {
    WxListBean<WxCouponVo> showMyList(Page page, Coupon coupon);

    WxListBean<Coupon> showList(Page page);

    int receiveCoupon(Integer couponId);

    Coupon exchangeCode(String code);

    int isExistCoupon(String code);

    void insertUser(CouponUser couponId);

    void insertDb(Coupon couponCanUse);

    List<Coupon> couponCanUse(int cartId,int grouponRulesId);
}

package com.cskaoyan.mall.service.wx.coupon.impl;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.service.wx.coupon.WxCouponService;
import com.cskaoyan.mall.util.*;
import com.cskaoyan.mall.vo.wx.coupon.CouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxCouponServiceImpl implements WxCouponService {
    @Autowired
    CouponMapper couponMapper;

    /**
     * 我的优惠券列表
     * author:zt
     * @param page
     * @param coupon
     * @return
     */
    @Override
    public WxListBean<Coupon> showMyList(Page page, Coupon coupon) {
        PageUtils.startPage(page);
        List<Coupon> coupons= couponMapper.queryCouponsByStatus(coupon.getStatus());
        return PageUtils.wxPage(coupons);
    }

    /**
     * 主页优惠券列表
     * author:zt
     * @param page
     * @return
     */
    @Override
    public WxListBean<Coupon> showList(Page page) {
        PageUtils.startPage(page);
        List<Coupon> list = couponMapper.queryAllCoupons();
        return PageUtils.wxPage(list);
    }

    @Override
    public int receiveCoupon(Integer couponId) {
        int flag=couponMapper.receiveCoupon(couponId);
        return flag;
    }

    @Override
    public Coupon exchangeCode(String code) {
Coupon coupon = couponMapper.queryCodeExchange(code);
        return coupon;
    }

    @Override
    public int isExistCoupon(String code) {
        int flag= couponMapper.isExistCoupon(code);
        return flag;
    }
}

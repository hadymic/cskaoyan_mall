package com.cskaoyan.mall.service.wx.coupon.impl;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.service.wx.coupon.WxCouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.StringUtils;
import com.cskaoyan.mall.vo.wx.coupon.CouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public CouponVo showMyList(Page page, Coupon coupon) {
        PageUtils.startPage(page);
        CouponVo couponVo = new CouponVo();
        List<Coupon> list = couponMapper.queryCouponsByStatus(coupon.getStatus());
        int count=  couponMapper.queryAll();
       couponVo.setCount(count);
       couponVo.setData(list);

        return couponVo;
    }

    /**
     * 主页优惠券列表
     * author:zt
     * @param page
     * @return
     */
    @Override
    public CouponVo showList(Page page) {
        PageUtils.startPage(page);
        CouponVo couponVo = new CouponVo();
        List<Coupon> list = couponMapper.queryAllCoupons();
        int count=  couponMapper.queryAll();
        couponVo.setCount(count);
        couponVo.setData(list);

        return couponVo;
    }

    @Override
    public int receiveCoupon(Integer couponId) {
        int flag=couponMapper.receiveCoupon(couponId);
        return flag;
    }
}

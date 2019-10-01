package com.cskaoyan.mall.service.promotion.impl;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.CouponUserMapper;
import com.cskaoyan.mall.service.promotion.CouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponUserMapper couponUserMapper;

    @Override
    public ListBean<Coupon> queryCoupons(Page page, String name, Integer type, Integer status) {
        PageUtils.startPage(page);
        if (!StringUtils.isEmpty(name)) {
            name = name.trim();
        }
        List<Coupon> list = couponMapper.queryCoupons(name, type, status);
        return PageUtils.page(list);
    }

    @Override
    public Coupon queryCouponById(int id) {
        return couponMapper.selectByPrimaryKey(id);
    }

    @Override
    public ListBean<CouponUser> queryCouponUser(Page page, int couponId, Integer status, Integer userId) {
        PageUtils.startPage(page);
        List<CouponUser> list = couponUserMapper.queryCouponUsers(couponId, status, userId);
        return PageUtils.page(list);
    }

    @Override
    public String insertCoupon(Coupon coupon) {
        //生成code
        //判断code是否重复
        //如果code重复则重新生成

        coupon.setAddTime(new Date());
        coupon.setDeleted(false);
        couponMapper.insertSelective(coupon);
        return null;
    }

    @Override
    public String updateCoupon(Coupon coupon) {
        //判断优惠券有效期状态
        coupon.setUpdateTime(new Date());
        couponMapper.updateByPrimaryKeySelective(coupon);
        return null;
    }

    @Override
    public boolean deleteCoupon(Integer id) {
        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setDeleted(true);
        coupon.setUpdateTime(new Date());
        return couponMapper.updateByPrimaryKeySelective(coupon) == 1;
    }
}

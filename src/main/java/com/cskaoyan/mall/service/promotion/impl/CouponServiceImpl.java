package com.cskaoyan.mall.service.promotion.impl;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.service.promotion.CouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public ListBean<Coupon> queryAllCoupons(Page page) {
        PageUtils.startPage(page);
        List<Coupon> list = couponMapper.queryAllCoupons();
        return PageUtils.page(list);
    }
}

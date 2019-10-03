package com.cskaoyan.mall.service.promotion.impl;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.CouponUserMapper;
import com.cskaoyan.mall.service.promotion.CouponService;
import com.cskaoyan.mall.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    private boolean validate(Coupon coupon, Date date) {
        //有效期开始时间大于结束时间，或结束时间小于当前时间
        if (coupon.getStartTime() != null && coupon.getEndTime() != null) {
            if (coupon.getStartTime().after(coupon.getEndTime()) || date.after(coupon.getEndTime())) {
                return false;
            }
        }
        //不能为负数
        if (BigDecimalUtils.isNegative(coupon.getMin()) ||
                BigDecimalUtils.isNegative(coupon.getDiscount()) ||
                coupon.getLimit() < 0 ||
                coupon.getTotal() < 0 ||
                coupon.getDays() < 0) {
            return false;
        }
        //满减金额大于最低消费
        if (coupon.getDiscount().compareTo(coupon.getMin()) > 0) {
            return false;
        }
        //限领数大于总数量
        if (coupon.getTotal() > 0 && coupon.getLimit() > 0 &&
                coupon.getLimit() > coupon.getTotal()) {
            return false;
        }
        return true;
    }

    @Override
    public Coupon insertCoupon(Coupon coupon) {
        Date date = new Date();
        if (!validate(coupon, date)) {
            return null;
        }
        //生成code
        String code;
        do {
            code = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            //判断code是否重复，如果code重复则重新生成
        } while (couponMapper.queryCodeCounts(code) == 1);
        coupon.setCode(code);

        coupon.setAddTime(date);
        coupon.setDeleted(false);
        return couponMapper.insertSelectKey(coupon) == 1 ? coupon : null;
    }

    @Override
    public Coupon updateCoupon(Coupon coupon) {
        Date date = new Date();
        if (!validate(coupon, date)) {
            return null;
        }
        coupon.setUpdateTime(date);
        return couponMapper.updateByPrimaryKey(coupon) == 1 ? coupon : null;
    }

    @Override
    public boolean deleteCoupon(Integer id) {
        //删除优惠券同时删除优惠券关联的user表信息
        couponUserMapper.deleteByCouponId(id);
        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setDeleted(true);
        coupon.setUpdateTime(new Date());
        return couponMapper.updateByPrimaryKeySelective(coupon) == 1;
    }
}

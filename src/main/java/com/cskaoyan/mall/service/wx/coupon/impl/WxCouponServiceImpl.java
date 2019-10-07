package com.cskaoyan.mall.service.wx.coupon.impl;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.CouponUserMapper;
import com.cskaoyan.mall.service.wx.coupon.WxCouponService;
import com.cskaoyan.mall.util.*;
import com.cskaoyan.mall.vo.wx.coupon.CouponVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WxCouponServiceImpl implements WxCouponService {
    @Autowired
    CouponMapper couponMapper;
@Autowired
    CouponUserMapper couponUserMapper;
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
        List<Coupon> coupons=couponUserMapper.queryCouponsByStatus(coupon.getStatus());
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
  //加入到用户的优惠券列表
    @Override
    public void insertUser(CouponUser couponUser) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        CouponUser user = new CouponUser();
        Coupon coupon= couponMapper.getCoupon(couponUser.getCouponId());
      user.setAddTime(new Date());
      user.setUpdateTime(new Date());
      user.setStartTime(coupon.getStartTime());
      user.setEndTime(coupon.getEndTime());
      user.setStatus(coupon.getStatus());
      user.setCouponId(coupon.getId());
      user.setUserId(userId);
      couponUserMapper.insertUser(user);
    }

    @Override
    public void insertDb(Coupon coupon) {
//        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        CouponUser user = new CouponUser();
        user.setUpdateTime(new Date());
        user.setStartTime(coupon.getStartTime());
        user.setEndTime(coupon.getEndTime());
        user.setStatus(coupon.getStatus());
        user.setCouponId(coupon.getId());
        user.setUserId(1);
        couponUserMapper.insertUser(user);
    }
}

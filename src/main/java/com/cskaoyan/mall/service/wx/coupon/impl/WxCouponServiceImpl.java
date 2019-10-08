package com.cskaoyan.mall.service.wx.coupon.impl;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.mapper.CartMapper;
import com.cskaoyan.mall.mapper.CouponMapper;
import com.cskaoyan.mall.mapper.CouponUserMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.service.wx.coupon.WxCouponService;
import com.cskaoyan.mall.util.*;
import com.cskaoyan.mall.vo.wx.coupon.CouponVo;
import com.cskaoyan.mall.vo.wx.coupon.WxCouponVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class WxCouponServiceImpl implements WxCouponService {
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    CouponUserMapper couponUserMapper;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    /**
     * 我的优惠券列表
     * author:zt
     *
     * @param page
     * @param coupon
     * @return
     */
    @Override
    public WxListBean<WxCouponVo> showMyList(Page page, Coupon coupon) {
        PageUtils.startPage(page);

        List<WxCouponVo> coupons = couponMapper.showByStatus(coupon.getStatus());

//        List<Coupon> coupons=couponUserMapper.queryCouponsByStatus(coupon.getStatus());
        return PageUtils.wxPage(coupons);
    }

    /**
     * 主页优惠券列表
     * author:zt
     *
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
        int flag = couponMapper.receiveCoupon(couponId);

        return flag;
    }

    @Override
    public Coupon exchangeCode(String code) {
        Coupon coupon = couponMapper.queryCodeExchange(code);
        return coupon;
    }

    @Override
    public int isExistCoupon(String code) {
        int flag = couponMapper.isExistCoupon(code);
        return flag;
    }

    //加入到用户的优惠券列表
    @Override
    public void insertUser(CouponUser couponUser) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        CouponUser user = new CouponUser();
        Coupon coupon = couponMapper.getCoupon(couponUser.getCouponId());
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
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        CouponUser user = new CouponUser();
        user.setUpdateTime(new Date());
        user.setStartTime(coupon.getStartTime());
        user.setEndTime(coupon.getEndTime());
        user.setStatus(coupon.getStatus());
        user.setCouponId(coupon.getId());
        user.setUserId(userId);
        couponUserMapper.insertUser(user);
    }

    /**
     * 优惠券列表
     *
     * @param cartId
     * @return
     */
    @Override
    public List<Coupon> couponCanUse(int cartId, int grouponRulesId) {
        Cart cart = cartMapper.selectByPrimaryKey(cartId);
        //购物车中商品的总价格
        Short number = cart.getNumber();
        BigDecimal price = cart.getPrice();
        //有团购
        BigDecimal discount = BigDecimal.ZERO;
        if (grouponRulesId != 0) {
            discount = grouponRulesMapper.getDiscount(grouponRulesId);
        }
        BigDecimal sum = price.multiply(BigDecimal.valueOf(number)).subtract(discount);
        //获得用户的id
        Integer userId = cart.getUserId();
        List<Coupon> coupons = new ArrayList<>();
        List<CouponUser> couponUsers = couponUserMapper.queryByUserId(userId);
        for (CouponUser couponUser : couponUsers) {
            Integer couponId = couponUser.getCouponId();
            Short status = couponUser.getStatus();
            Date startTime = couponUser.getStartTime();
            Date endTime = couponUser.getEndTime();

            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            Date date = new Date();
            //对优惠券进行判断
            BigDecimal min = coupon.getMin();

            if (sum.compareTo(min) > -1 && status == 0)
                if (startTime == null && endTime == null) {
                    Calendar cl = Calendar.getInstance();
                    cl.setTime(couponUser.getAddTime());
                    cl.add(Calendar.DATE, coupon.getDays());
                    Date time = cl.getTime();
                    if (date.after(time)) {
                        coupons.add(coupon);
                    }
                } else {
                    if (date.after(startTime) && date.before(endTime)) {
                        coupons.add(coupon);
                    }
                }
        }
        return coupons;
    }
}

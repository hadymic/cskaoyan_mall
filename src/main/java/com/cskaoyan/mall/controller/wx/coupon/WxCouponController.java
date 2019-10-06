package com.cskaoyan.mall.controller.wx.coupon;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.service.wx.coupon.WxCouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.WxListBean;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.coupon.CouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wx/coupon")
public class WxCouponController {

    @Autowired
    WxCouponService wxCouponService;

    /**
     * 个人主页优惠券列表
     *
     * @param page
     * @param coupon
     * @return
     * @author:zt
     */
    @RequestMapping("mylist")
    public BaseRespVo showMyList(Page page, Coupon coupon) {
        WxListBean<Coupon> coupons= wxCouponService.showMyList(page, coupon);
        return BaseRespVo.success(coupons);
    }


    /**
     * 优惠券列表
     * author:zt
     *
     * @param page
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo showList(Page page) {
        WxListBean<Coupon> coupons = wxCouponService.showList(page);
        return BaseRespVo.success(coupons);
    }


    /**
     * 领取优惠券
     * author:zt
     *
     * @param couponId
     * @return
*/
    @PostMapping("receive")
    public BaseRespVo receive(@RequestBody CouponUser couponId) {
        Integer flag = wxCouponService.receiveCoupon(couponId.getCouponId());
        if (flag == 1) {
            return BaseRespVo.success(null);
        } else return BaseRespVo.fail("优惠券已领取完");
    }



    @PostMapping("exchange")
    public BaseRespVo exchangeCode(@RequestBody Coupon coupon){
       int flag= wxCouponService.isExistCoupon(coupon.getCode());
       if(flag==1) {
           Coupon couponCanUse = wxCouponService.exchangeCode(coupon.getCode());
           return BaseRespVo.success(couponCanUse);
       }
       else return  BaseRespVo.fail("优惠券不正确");
    }














}

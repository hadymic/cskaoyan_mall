package com.cskaoyan.mall.controller.wx.coupon;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.service.wx.coupon.WxCouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/coupon")
public class WxCouponController {

    @Autowired
    WxCouponService wxCouponService;
    @RequestMapping("mylist")
    public BaseRespVo showList(Page page, Coupon coupon){
       ListBean<Coupon> couponListBean= wxCouponService.showList(page,coupon);
       return  BaseRespVo.success(couponListBean);
    }
}

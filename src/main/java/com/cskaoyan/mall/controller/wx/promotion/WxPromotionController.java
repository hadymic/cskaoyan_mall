package com.cskaoyan.mall.controller.wx.promotion;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.service.promotion.CouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.BadAttributeValueExpException;

//@RestController
//@RequestMapping("wx/coupon")
//public class WxPromotionController {
//    @Autowired
//    CouponService couponService;
//    @RequestMapping("myllist")
//    public BaseRespVo list(Page page, Coupon coupon){
//          ListBean<Coupon>couponListBean= couponService.showList(page,coupon);
//          return  BaseRespVo.success(couponListBean);
//    }
//}

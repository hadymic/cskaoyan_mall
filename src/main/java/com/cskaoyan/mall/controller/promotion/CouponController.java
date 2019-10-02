package com.cskaoyan.mall.controller.promotion;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.service.promotion.CouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 优惠券管理
 *
 * @author hadymic
 */
@RestController
@RequestMapping("admin/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    /**
     * 显示优惠券列表
     *
     * @param page
     * @return
     */
    @GetMapping("list")
    public BaseRespVo showCouponList(Page page, String name, Integer type, Integer status) {
        ListBean<Coupon> couponListBean = couponService.queryCoupons(page, name, type, status);
        return BaseRespVo.success(couponListBean);
    }

    /**
     * 优惠券详情
     *
     * @param id
     * @return
     */
    @GetMapping("read")
    public BaseRespVo couponInfo(int id) {
        Coupon coupon = couponService.queryCouponById(id);
        return BaseRespVo.success(coupon);
    }

    /**
     * 优惠券与用户信息
     *
     * @param page
     * @param couponId
     * @param status
     * @param userId
     * @return
     */
    @GetMapping("listuser")
    public BaseRespVo queryCouponUser(Page page, int couponId, Integer status, Integer userId) {
        ListBean<CouponUser> couponListBean = couponService.queryCouponUser(page, couponId, status, userId);
        return BaseRespVo.success(couponListBean);
    }

    /**
     * 添加优惠券
     *
     * @param coupon
     * @return
     */
    @PostMapping("create")
    public BaseRespVo insertCoupon(@RequestBody Coupon coupon) {
        Coupon newCoupon = couponService.insertCoupon(coupon);
        return newCoupon != null ? BaseRespVo.success(newCoupon) : BaseRespVo.fail("优惠券添加失败");
    }

    /**
     * 修改优惠券
     *
     * @param coupon
     * @return
     */
    @PostMapping("update")
    public BaseRespVo updateCoupon(@RequestBody Coupon coupon) {
        Coupon newCoupon = couponService.updateCoupon(coupon);
        return newCoupon != null ? BaseRespVo.success(newCoupon) : BaseRespVo.fail("优惠券更新失败");
    }

    /**
     * 删除优惠券
     *
     * @param coupon
     * @return
     */
    @PostMapping("delete")
    public BaseRespVo deleteCoupon(@RequestBody Coupon coupon) {
        boolean flag = couponService.deleteCoupon(coupon.getId());
        return flag ? BaseRespVo.success(null) : BaseRespVo.fail("优惠券删除失败");
    }
}

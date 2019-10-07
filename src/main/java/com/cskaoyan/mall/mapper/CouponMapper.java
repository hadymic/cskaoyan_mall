package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    List<Coupon> queryCoupons(@Param("name") String name, @Param("type") Integer type, @Param("status") Integer status);


    List<Coupon> queryAllCoupons();


    int insertSelectKey(Coupon record);



    int queryCodeCounts(String code);

    List<Coupon> queryCouponsByStatus(Short status);

    int queryAll();

    int receiveCoupon(Integer couponId);

    Coupon queryCodeExchange(String code);

    int isExistCoupon(String code);

    void insertUserCoupon(CouponUser couponId);



    Coupon getCoupon(Integer id);
}




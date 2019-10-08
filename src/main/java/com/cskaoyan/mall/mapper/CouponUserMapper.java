package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.CouponUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CouponUser record);

    int insertSelective(CouponUser record);

    CouponUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponUser record);

    int updateByPrimaryKey(CouponUser record);

    List<CouponUser> queryCouponUsers(@Param("couponId") int couponId, @Param("status") Integer status, @Param("userId") Integer userId);

    void deleteByCouponId(Integer couponId);

    List<Coupon> queryCouponsByStatus(Short status);

    void insertUser(CouponUser user);

    List<CouponUser> queryByUserId(Integer userId);

    /**
     * 查询CouponUser
     * @param orderId
     * @return
     */
    CouponUser queryCouponUserByOrderId(Integer orderId);
}

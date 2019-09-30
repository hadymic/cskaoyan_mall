package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.CouponUser;

import java.util.List;

public interface CouponUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CouponUser record);

    int insertSelective(CouponUser record);

    CouponUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponUser record);

    int updateByPrimaryKey(CouponUser record);

    List<CouponUser> queryCouponUsersByCouponId(int couponId);
}
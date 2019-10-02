package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Coupon;
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

<<<<<<< HEAD


    List<Coupon> queryAllCoupons();


=======
    List<Coupon> queryAllCoupons();

>>>>>>> a302b6eccf01227b9e6c92bc684424f37f791d38
    int insertSelectKey(Coupon record);

}

<<<<<<< HEAD



=======
>>>>>>> a302b6eccf01227b9e6c92bc684424f37f791d38

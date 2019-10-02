package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Order;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.cskaoyan.mall.vo.statisticalform.StatOrderVo;


public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    /**
     * jszza:查询订单详细
     * @param id
     * @return
     */
    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


    /**
     * jszza:分页查询order
     * @param userId
     * @param orderSn
     * @param orderStatusArray
     * @return
     */
    List<Order> queryOrderList(@Param("userId") Integer userId, @Param("orderSn") String orderSn, @Param("orderStatusArray") Integer[] orderStatusArray);

    /**
     * Zeng-jz:查询每日的订单信息
     * @return
     */
    List<StatOrderVo> selectOrderByDay();
}

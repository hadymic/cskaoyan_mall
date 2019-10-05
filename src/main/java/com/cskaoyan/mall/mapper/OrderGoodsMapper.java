package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.vo.ordermanagement.UserOrderGoods;
import com.cskaoyan.mall.vo.statisticalform.StatOrderGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderGoods record);

    int insertSelective(OrderGoods record);

    OrderGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderGoods record);

    int updateByPrimaryKey(OrderGoods record);

    /**
     * jszza:根据orderId查询orderGoods
     * @param orderId
     * @return
     */
    List<OrderGoods> queryOrderGoodsByOrderId(@Param("orderId") int orderId);

    /**
     * Zeng-jz:查询每日的下单商品信息
     * @return
     */
    List<StatOrderGoodsVo> selectOrdersByDay();

    /**
     * 根据orderId查询orderGoods简要信息
     * @param orderId
     * @return
     */
    List<UserOrderGoods> queryOrderGoodsList(@Param("orderId") Integer orderId);
}

package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.OrderGoods;
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
}

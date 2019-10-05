package com.cskaoyan.mall.service.mallmanager.impl;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.mallmanager.OrderService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.ordermanagement.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jszza
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public ListBean<Order> queryOrderList(Page page, Integer userId, String orderSn, Integer[] orderStatusArray) {
        PageUtils.startPage(page);
        List<Order> orderList = orderMapper.queryOrderList(userId, orderSn, orderStatusArray);
        return PageUtils.page(orderList);
    }

    @Override
    public Map<String, Object> queryOrderDetail(int id) {
        Order order = orderMapper.selectByPrimaryKey(id);
        List<OrderGoods> orderGoods = orderGoodsMapper.queryOrderGoodsByOrderId(id);
        User user = userMapper.queryUserForOrder(order.getUserId());
        Map<String, Object> orderDetail = new HashMap<>(3);
        orderDetail.put("order", order);
        orderDetail.put("orderGoods", orderGoods);
        orderDetail.put("user", user);
        return orderDetail;
    }

    @Override
    public Order updateShip(ShipVo shipVo) {
        Order order = new Order();
        order.setId(shipVo.getOrderId());
        order.setShipChannel(shipVo.getShipChannel());
        order.setShipSn(shipVo.getShipSn());
        order.setOrderStatus((short) 301);
        orderMapper.updateByPrimaryKeySelective(order);
        return order;
    }

    @Override
    public Order updateRefund(RefundVo refundVo) {
        Order order = new Order();
        order.setId(refundVo.getOrderId());
        order.setOrderStatus((short) 203);
        orderMapper.updateByPrimaryKeySelective(order);
        return order;
    }

    @Override
    public ListBeanForOrder<UserOrdersVo> queryUserOrders(Integer userId, Page page, Integer showType) {
        PageUtils.startPage(page);
        List<UserOrdersVo> orderList = orderMapper.queryUserOrders(userId, showType);
        PageInfo<UserOrdersVo> pageInfo = new PageInfo<>(orderList);
        for (UserOrdersVo userOrdersVo : orderList) {
            List<UserOrderGoods> goodsList = orderGoodsMapper.queryOrderGoodsList(userOrdersVo.getId());
            userOrdersVo.setGoodsList(goodsList);
            HandleOption handleOption = HandleOption.get(userOrdersVo.getOrderStatus(), goodsList.get(0).getComment() == 0);
            userOrdersVo.setOrderStatusText(handleOption.getStatusText());
            userOrdersVo.setIsGroupin(userOrdersVo.getGroupon() != null);
        }
        return new ListBeanForOrder<>(orderList, pageInfo.getTotal(), pageInfo.getPageNum());
    }
}

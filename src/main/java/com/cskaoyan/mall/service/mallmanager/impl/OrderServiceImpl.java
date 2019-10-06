package com.cskaoyan.mall.service.mallmanager.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.mallmanager.OrderService;
import com.cskaoyan.mall.util.*;
import com.cskaoyan.mall.vo.ordermanagement.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.math.BigDecimal;
import java.util.*;

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
    @Autowired
    CartMapper cartMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CouponMapper couponMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;

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
        order.setUpdateTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
        return order;
    }

    @Override
    public Order updateRefund(RefundVo refundVo) {
        Order order = new Order();
        order.setId(refundVo.getOrderId());
        order.setOrderStatus((short) 203);
        order.setUpdateTime(new Date());
        orderMapper.updateByPrimaryKeySelective(order);
        return order;
    }

    @Override
    public WxListBean<UserOrdersVo> queryUserOrders(Integer userId, Page page, Integer showType) {
        PageUtils.startPage(page);
        List<UserOrdersVo> orderList = orderMapper.queryUserOrders(userId, showType);
        PageInfo<UserOrdersVo> pageInfo = new PageInfo<>(orderList);
        for (UserOrdersVo userOrdersVo : pageInfo.getList()) {
            List<UserOrderGoods> goodsList = orderGoodsMapper.queryOrderGoodsList(userOrdersVo.getId());
            userOrdersVo.setGoodsList(goodsList);
            HandleOption handleOption = HandleOption.get(userOrdersVo.getOrderStatus(), goodsList.get(0).getComment() == 0);
            userOrdersVo.setHandleOption(handleOption);
            userOrdersVo.setOrderStatusText(handleOption.getStatusText());
            userOrdersVo.setIsGroupin(userOrdersVo.getGroupon() != null);
            userOrdersVo.setGroupon(null);
        }
        return new WxListBean<>(pageInfo.getList(), pageInfo.getTotal(), pageInfo.getPages());
    }

    @Override
    public Map queryUserOrderInfo(Integer orderId) {
        Map<String, Object> map = new HashMap<>(2);
        List<OrderGoods> orderGoods = orderGoodsMapper.queryOrderGoodsByOrderId(orderId);
        OrderInfo orderInfo = orderMapper.queryOrderInfo(orderId);
        if (orderInfo.getEndTime().getTime() < System.currentTimeMillis()){
            for (OrderGoods orderGood : orderGoods) {
                if (orderGood.getComment() == 0) {
                    orderGood.setComment(-1);
                    OrderGoods orderGoods1 = new OrderGoods();
                    orderGoods1.setId(orderGood.getId());
                    orderGoods1.setComment(-1);
                    orderGoods1.setUpdateTime(new Date());
                    orderGoodsMapper.updateByPrimaryKeySelective(orderGoods1);
                }
            }
        }
        HandleOption handleOption = HandleOption.get(orderInfo.getOrderStatus(), orderGoods.get(0).getComment() == 0);
        orderInfo.setHandleOption(handleOption);
        orderInfo.setOrderStatusText(handleOption.getStatusText());
        map.put("orderGoods", orderGoods);
        map.put("orderInfo", orderInfo);
        return map;
    }

    @Override
    public Map insertOrder(int userId, SubmitVo submitVo) {
        List<Cart> carts = cartMapper.queryByUserId(userId, true);
        int i = cartMapper.deleteByUserId(userId, true);
        Address address1 = addressMapper.selectAddressById(submitVo.getAddressId());
        Order order = new Order();
        order.setUserId(userId);
        // year + month + day + random
        order.setOrderSn("" + 20191008 + RandomUtils.getRandom(6));
        order.setOrderStatus((short)101);
        order.setConsignee(address1.getName());
        order.setMobile(address1.getMobile());
        order.setAddress(address1.getDetailedAddress());
        order.setMessage(submitVo.getMessage());
        BigDecimal goodsPrice = BigDecimal.ZERO;
        for (Cart cart : carts) {
            BigDecimal num = new BigDecimal(cart.getNumber());
            BigDecimal totalPrice = cart.getPrice().multiply(num);
            goodsPrice = goodsPrice.add(totalPrice);
        }
        order.setGoodsPrice(goodsPrice);
        BigDecimal freightPrice = new BigDecimal(10);
        order.setFreightPrice(freightPrice);
        BigDecimal couponPrice = BigDecimal.ZERO;
        if (submitVo.getCouponId() != 0 && submitVo.getCouponId() != -1) {
            Coupon coupon = couponMapper.selectByPrimaryKey(submitVo.getCouponId());
            couponPrice = coupon.getDiscount();
        }
        order.setCouponPrice(couponPrice);
        BigDecimal integralPrice = BigDecimal.valueOf(0);
        order.setIntegralPrice(integralPrice);

        BigDecimal grouponPrice = BigDecimal.ZERO;
        if (submitVo.getGrouponRulesId() != 0) {
            GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(submitVo.getGrouponRulesId());
            grouponPrice = grouponRules.getDiscount();
        }
        order.setGrouponPrice(grouponPrice);

        BigDecimal orderPrice = goodsPrice.add(freightPrice).subtract(couponPrice);
        order.setOrderPrice(orderPrice);
        order.setActualPrice(orderPrice.subtract(integralPrice));
        order.setPayId("" + 20191008 + RandomUtils.getRandom(6));
        order.setAddTime(new Date());
        order.setUpdateTime(new Date());
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+1);
        Date date = curr.getTime();
        order.setEndTime(date);
        order.setDeleted(false);
        orderMapper.insertSelective(order);
        Map<String, Integer> data = new HashMap<>(1);
        for (Cart cart : carts) {
            OrderGoods orderGoods = new OrderGoods();
            orderGoods.setOrderId(order.getId());
            orderGoods.setGoodsId(cart.getGoodsId());
            orderGoods.setGoodsName(cart.getGoodsName());
            orderGoods.setGoodsSn(cart.getGoodsSn());
            orderGoods.setProductId(cart.getProductId());
            orderGoods.setNumber(cart.getNumber());
            orderGoods.setPrice(cart.getPrice());
            orderGoods.setSpecifications(cart.getSpecifications());
            orderGoods.setPicUrl(cart.getPicUrl());
            orderGoods.setComment(0);
            orderGoods.setAddTime(new Date());
            orderGoods.setUpdateTime(new Date());
            orderGoods.setDeleted(false);
            orderGoodsMapper.insert(orderGoods);
        }
        data.put("orderId", order.getId());
        return data;
    }

    @Override
    public int updateOrderPrepay(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short) 201);
        List<OrderGoods> orderGoodsList = orderGoodsMapper.queryOrderGoodsForPay(orderId);
        for (OrderGoods orderGoods : orderGoodsList) {
            orderGoods.setUpdateTime(new Date());
            GoodsProduct goodsProduct = new GoodsProduct();
            goodsProduct.setId(orderGoods.getProductId());
            goodsProduct.setNumber((int) orderGoods.getNumber());
            goodsProductMapper.updateByPrimaryKeySelective(goodsProduct);
        }
        order.setPayTime(new Date());
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int updateOrderCancel(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short)102);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int updateOrderRefund(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short)202);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int deleteOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setDeleted(true);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int updateOrderConfirm(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short)401);
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public OrderGoods queryOrderGoods(Integer orderId, Integer goodsId) {
        return orderGoodsMapper.queryOrderGoods(orderId, goodsId);
    }
}

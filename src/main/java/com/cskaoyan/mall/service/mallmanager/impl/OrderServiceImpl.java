package com.cskaoyan.mall.service.mallmanager.impl;

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
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    SystemMapper systemMapper;

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
        List<OrderGoods> orderGoodsList = orderGoodsMapper.queryOrderGoodsForPay(refundVo.getOrderId());
        for (OrderGoods orderGoods : orderGoodsList) {
            GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(orderGoods.getProductId());
            goodsProduct.setNumber(goodsProduct.getNumber() + orderGoods.getNumber());
            goodsProduct.setUpdateTime(new Date());
            goodsProductMapper.updateByPrimaryKey(goodsProduct);
        }
        int unconfirm = Integer.parseInt(systemMapper.selectByKeyName("cskaoyan_mall_order_unconfirm"));
        Date endDay = TimeUtils.getEndDay(unconfirm);
        order.setEndTime(endDay);
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
            HandleOption handleOption = HandleOption.get(userOrdersVo.getOrderStatus(), userOrdersVo.getComments() != 0);
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
        List<OrderGoods> orderGoodsList = orderGoodsMapper.queryOrderGoodsByOrderId(orderId);
        OrderInfo orderInfo = orderMapper.queryOrderInfo(orderId);

        boolean comment = true;
        if(judgeTimeFromOrderInfo(orderInfo)){
            comment = false;
        }
        HandleOption handleOption = HandleOption.get(orderInfo.getOrderStatus(), comment);
        orderInfo.setHandleOption(handleOption);
        orderInfo.setOrderStatusText(handleOption.getStatusText());
        if (orderInfo.getOrderStatus() == 301) {
            ExpressInfo expressInfo = orderMapper.queryExpressInfo(orderId);
            map.put("expressInfo",expressInfo);
        }
        map.put("orderGoods", orderGoodsList);
        map.put("orderInfo", orderInfo);
        return map;
    }

    @Override
    public Map insertOrder(int userId, SubmitVo submitVo) {
        List<Cart> carts = new ArrayList<>();
        if (submitVo.getCartId() == 0) {
            carts = cartMapper.queryByUserId(userId,true);
            int i = cartMapper.deleteByUserId(userId, true);
        } else {
            Cart cart = cartMapper.selectByPrimaryKey(submitVo.getCartId());
            carts.add(cart);
            cart.setDeleted(true);
            cartMapper.updateByPrimaryKey(cart);
        }

        Address address1 = addressMapper.selectAddressById(submitVo.getAddressId());
        Order order = new Order();
        order.setUserId(userId);
        // year + month + day + random
        String dateString = TimeUtils.getDateString(new Date());
        order.setOrderSn(dateString + RandomUtils.getRandom(6));
        order.setOrderStatus((short)101);
        order.setConsignee(address1.getName());
        order.setMobile(address1.getMobile());
        order.setAddress(address1.getOrderAddress());
        order.setMessage(submitVo.getMessage());
        BigDecimal goodsPrice = BigDecimal.ZERO;
        Short comments = 0;
        for (Cart cart : carts) {
            BigDecimal totalPrice = cart.getPrice().multiply(new BigDecimal(cart.getNumber()));
            goodsPrice = goodsPrice.add(totalPrice);
            comments = (short) (comments + cart.getNumber());
        }
        order.setGoodsPrice(goodsPrice);
        order.setComments(comments);
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

        order.setAddTime(new Date());
        order.setUpdateTime(new Date());
        int unpaid = Integer.parseInt(systemMapper.selectByKeyName("cskaoyan_mall_order_unpaid"));
        Date endMinutes = TimeUtils.getEndMinutes(unpaid);
        order.setEndTime(endMinutes);

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
        List<OrderGoods> orderGoodsList = orderGoodsMapper.queryOrderGoodsForPay(order.getId());
        boolean flag = true;
        for (OrderGoods orderGoods : orderGoodsList) {
            GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(orderGoods.getProductId());
            int num = goodsProduct.getNumber() - orderGoods.getNumber();
            if (num < 0) {
                flag = false;
            }
        }
        if (flag) {
            for (OrderGoods orderGoods : orderGoodsList) {
                GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(orderGoods.getProductId());
                goodsProduct.setNumber(goodsProduct.getNumber() - orderGoods.getNumber());
                goodsProduct.setUpdateTime(new Date());
                goodsProductMapper.updateByPrimaryKey(goodsProduct);
            }

            if (submitVo.getGrouponRulesId() != 0) {
                Groupon groupon = new Groupon();
                groupon.setOrderId(order.getId());
                groupon.setRulesId(submitVo.getGrouponRulesId());
                groupon.setUserId(userId);
                groupon.setCreatorUserId(userId);
                groupon.setAddTime(new Date());
                groupon.setUpdateTime(new Date());
                groupon.setPayed(false);
                groupon.setDeleted(false);
                grouponMapper.insert(groupon);
                grouponMapper.updateGrouponId(groupon.getId());
            }
            data.put("orderId", order.getId());
        } else {
            data = null;
        }
        return data;
    }

    @Override
    public int updateOrderPrepay(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        order.setOrderStatus((short) 201);
        String dateString = TimeUtils.getDateString(new Date());
        order.setPayId(dateString + RandomUtils.getRandom(6));
        order.setPayTime(new Date());
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int updateOrderCancel(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        List<OrderGoods> orderGoodsList = orderGoodsMapper.queryOrderGoodsForPay(orderId);
        for (OrderGoods orderGoods : orderGoodsList) {
            GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(orderGoods.getProductId());
            goodsProduct.setNumber(goodsProduct.getNumber() + orderGoods.getNumber());
            goodsProduct.setUpdateTime(new Date());
            goodsProductMapper.updateByPrimaryKey(goodsProduct);
        }
        order.setOrderStatus((short)102);
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int updateOrderRefund(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short)202);
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int deleteOrder(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setDeleted(true);
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int updateOrderConfirm(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setOrderStatus((short)401);
        int comment = Integer.parseInt(systemMapper.selectByKeyName("cskaoyan_mall_order_comment"));
        Date endDay = TimeUtils.getEndDay(comment);
        order.setEndTime(endDay);
        order.setUpdateTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public OrderGoods queryOrderGoods(Integer orderId, Integer goodsId) {
        return orderGoodsMapper.queryOrderGoods(orderId, goodsId);
    }


    private boolean judgeTimeFromOrderInfo(OrderInfo orderInfo){
        if (orderInfo.getComments() == 0){
            return true;
        }
        if (orderInfo.getEndTime().getTime() > System.currentTimeMillis()){
            return false;
        } else {
            List<OrderGoods> orderGoodsList = orderGoodsMapper.queryOrderGoodsByOrderId(orderInfo.getId());
            for (OrderGoods orderGoods : orderGoodsList) {
                if (orderGoods.getComment() == 0) {
                    orderGoods.setComment(-1);
                    orderGoods.setUpdateTime(new Date());
                    orderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
                }
            }
            Order order = new Order();
            order.setId(orderInfo.getId());
            order.setComments((short)0);
            order.setUpdateTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
            return true;
        }
    }
}

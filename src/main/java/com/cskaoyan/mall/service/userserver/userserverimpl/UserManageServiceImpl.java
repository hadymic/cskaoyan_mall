package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.userserver.UserManageService;
import com.cskaoyan.mall.util.*;
import com.cskaoyan.mall.vo.user.UserIndexVo;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.page.PageMethod;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;

    @Override
    public ListBean getDispalyList(Page utipage, String username, String mobile) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        username = StringUtils.addPercent(username);
        mobile = StringUtils.addPercent(mobile);
        List<User> users = userMapper.selectByNameAndMobile(username, mobile);
        return PageUtils.page(users);
    }

    @Override
    public UserIndexVo queryUserOrders(Integer userId) {
        UserIndexVo userIndexVo = new UserIndexVo();
        List<Order> orders = orderMapper.queryUserOrdersNumber(userId);
        Integer uncomment = 0;
        Integer unpaid = 0;
        Integer unrecv = 0;
        Integer unship = 0;
        for (Order order : orders) {
            switch (order.getOrderStatus()) {
                case 101:
                    unpaid++;
                    break;
                case 201:
                    unship++;
                    break;
                case 301:
                    unrecv++;
                    break;
                case 401:
                case 402:
                    if (!judgeTimeFromOrder(order)){
                        uncomment++;
                    }
                    break;
                default:
                    break;
            }
        }
        userIndexVo.setUncomment(uncomment);
        userIndexVo.setUnpaid(unpaid);
        userIndexVo.setUnrecv(unrecv);
        userIndexVo.setUnship(unship);
        return userIndexVo;
    }

    private boolean judgeTimeFromOrder(Order order){
        if (order.getComments() == 0){
            return true;
        }
        if (order.getEndTime().getTime() > System.currentTimeMillis()){
            return false;
        } else {
            List<OrderGoods> orderGoodsList = orderGoodsMapper.queryOrderGoodsByOrderId(order.getId());
            for (OrderGoods orderGoods : orderGoodsList) {
                if (orderGoods.getComment() == 0) {
                    orderGoods.setComment(-1);
                    orderGoods.setUpdateTime(new Date());
                    orderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
                }
            }
            order.setComments((short)0);
            order.setUpdateTime(new Date());
            orderMapper.updateByPrimaryKeySelective(order);
            return true;
        }
    }
}

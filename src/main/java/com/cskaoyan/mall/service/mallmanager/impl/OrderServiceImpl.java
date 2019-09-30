package com.cskaoyan.mall.service.mallmanager.impl;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.service.mallmanager.OrderService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jszza
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public ListBean<Order> queryOrderList(Page page) {
        List<Order> orderList= orderMapper.queryOrderList();
        return PageUtils.page(page, orderList);
    }
}

package com.cskaoyan.mall.service.mallmanager.impl;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.service.mallmanager.OrderService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.stereotype.Service;

/**
 * @author jszza
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public BaseRespVo<ListBean<Order>> queryOrderList(Page page) {

        return null;
    }
}

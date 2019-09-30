package com.cskaoyan.mall.service.mallmanager;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;

import java.util.Map;

/**
 * @author jszza
 */
public interface OrderService {

    /**
     * 分页查询order
     * @param page
     * @param userId
     * @param orderSn
     * @param orderStatusArray
     * @return
     */
    ListBean<Order> queryOrderList(Page page, Integer userId, String orderSn, Integer[] orderStatusArray);

    /**
     * 查询订单详细
     * @param id
     * @return
     */
    Map<String, Object> queryOrderDetail(int id);
}

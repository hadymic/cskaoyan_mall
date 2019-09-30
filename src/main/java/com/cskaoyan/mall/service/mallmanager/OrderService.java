package com.cskaoyan.mall.service.mallmanager;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;

/**
 * @author jszza
 */
public interface OrderService {
    /**
     * 分页查询order
     * @param page
     * @return
     */
    ListBean<Order> queryOrderList(Page page);
}

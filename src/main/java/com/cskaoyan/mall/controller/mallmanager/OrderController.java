package com.cskaoyan.mall.controller.mallmanager;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.service.mallmanager.OrderService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jszza
 */
@RestController
@RequestMapping("admin/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 分页查询order
     * @param page
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo<ListBean<Order>> orderList(Page page){
        return orderService.queryOrderList(page);
    }
}

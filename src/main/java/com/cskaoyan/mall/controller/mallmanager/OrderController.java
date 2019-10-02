package com.cskaoyan.mall.controller.mallmanager;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.service.mallmanager.OrderService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ordermanagement.RefundVo;
import com.cskaoyan.mall.vo.ordermanagement.ShipVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


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
    public BaseRespVo orderList(Page page, Integer userId, String orderSn, Integer[] orderStatusArray){
        return BaseRespVo.success(orderService.queryOrderList(page,userId,orderSn,orderStatusArray));
    }

    /**
     * 查询订单详细
     * @param id
     * @return
     */
    @RequestMapping("detail")
    public BaseRespVo detail(int id){
        Map<String, Object> orderDetail = orderService.queryOrderDetail(id);
        if (orderDetail != null) {
            return BaseRespVo.success(orderDetail);
        }
        return BaseRespVo.fail("没有该商品，请合法查询");
    }

    /**
     * 修改订单发货状态
     * @param shipVo
     * @return
     */
    @RequestMapping("ship")
    public BaseRespVo ship(@RequestBody ShipVo shipVo){
        return BaseRespVo.success(orderService.updateShip(shipVo));
    }

    /**
     * 修改订单退款
     * @param refundVo
     * @return
     */
    @RequestMapping("refund")
    public BaseRespVo refund(@RequestBody RefundVo refundVo){
        return BaseRespVo.success(orderService.updateRefund(refundVo));
    }
}

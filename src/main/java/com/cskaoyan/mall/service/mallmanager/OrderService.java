package com.cskaoyan.mall.service.mallmanager;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.ordermanagement.ListBeanForOrder;
import com.cskaoyan.mall.vo.ordermanagement.RefundVo;
import com.cskaoyan.mall.vo.ordermanagement.ShipVo;
import com.cskaoyan.mall.vo.ordermanagement.UserOrdersVo;

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

    /**
     * 修改订单发货状态
     * @param shipVo
     * @return
     */
    Order updateShip(ShipVo shipVo);

    /**
     * 修改订单退款
     * @param refundVo
     * @return
     */
    Order updateRefund(RefundVo refundVo);


    /**
     * 查询用户的订单
     * @param userId
     * @param page
     * @param showType
     * @return
     */
    ListBeanForOrder<UserOrdersVo> queryUserOrders(Integer userId, Page page, Integer showType);
}

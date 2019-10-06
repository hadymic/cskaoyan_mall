package com.cskaoyan.mall.service.mallmanager;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.WxListBean;
import com.cskaoyan.mall.vo.ordermanagement.RefundVo;
import com.cskaoyan.mall.vo.ordermanagement.ShipVo;
import com.cskaoyan.mall.vo.ordermanagement.SubmitVo;
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
    WxListBean<UserOrdersVo> queryUserOrders(Integer userId, Page page, Integer showType);

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    Map queryUserOrderInfo(Integer orderId);

    /**
     * 添加订单，取消在购物车的相关物品
     * @param userId
     * @param submitVo
     * @return
     */
    Map insertOrder(int userId, SubmitVo submitVo);

    /**
     * 支付订单
     * @param orderId
     * @return
     */
    int updateOrderPrepay(Integer orderId);

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    int updateOrderCancel(Integer orderId);

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    int updateOrderRefund(Integer orderId);

    /**
     * 删除订单
     * @param orderId
     * @return
     */
    int deleteOrder(Integer orderId);

    /**
     * 确认收货
     * @param orderId
     * @return
     */
    int updateOrderConfirm(Integer orderId);

    /**
     * 查询要评价的商品
     * @param orderId
     * @param goodsId
     * @return
     */
    OrderGoods queryOrderGoods(Integer orderId, Integer goodsId);
}

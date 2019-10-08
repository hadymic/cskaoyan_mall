package com.cskaoyan.mall.controller.wx.order;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.service.mallmanager.OrderService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ordermanagement.SubmitVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author jszza
 */
@RestController
@RequestMapping("wx/order")
public class WxOrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CommentService commentService;

    /**
     * 查询orders
     * @param page
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo orderList(Page page, Integer showType){
        Integer id = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        return BaseRespVo.success(orderService.queryUserOrders(id,page,showType));
    }

    /**
     * 提交订单
     * 如果在这里提交后不支付被系统取消订单，应该是数据库的时区问题，可以在
     * com.cskaoyan.mall.service.userserver.userserverimpl.UserManageServiceImpl#queryUserOrders
     * 中取消对unpaid的判断
     * @return
     */
    @RequestMapping("submit")
    public BaseRespVo submit(@RequestBody SubmitVo submitVo){
        Integer id = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        Map data = orderService.insertOrder(id, submitVo);
        if (data == null) {
            return BaseRespVo.fail("货源不足，付款失败");
        }
        return BaseRespVo.success(data);
    }

    /**
     * 订单的预定支付会话
     * @return
     */
    @RequestMapping("prepay")
    public BaseRespVo prepay(@RequestBody Map map){
        Object orderId1 = map.get("orderId");
        Integer orderId = 0;
        if (orderId1 instanceof String) {
            orderId = Integer.valueOf((String)orderId1);
        } else if (orderId1 instanceof Integer){
            orderId = (Integer) orderId1;
        }
        orderService.updateOrderPrepay(orderId);
        return BaseRespVo.success("成功");
    }

    /**
     * 订单详情
     * @return
     */
    @RequestMapping("detail")
    public BaseRespVo detail(Integer orderId){
        return BaseRespVo.success(orderService.queryUserOrderInfo(orderId));
    }

    /**
     * 取消订单
     * @return
     */
    @RequestMapping("cancel")
    public BaseRespVo cancel(@RequestBody Map map){
        Integer orderId = (Integer) map.get("orderId");
        int i = orderService.updateOrderCancel(orderId);
        return BaseRespVo.success("成功");
    }

    /**
     * 退款取消订单
     * @return
     */
    @RequestMapping("refund")
    public BaseRespVo refund(@RequestBody Map map){
        Integer orderId = (Integer) map.get("orderId");
        int i = orderService.updateOrderRefund(orderId);
        return BaseRespVo.success(null);
    }

    /**
     * 删除订单
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Map map){
        Integer orderId = (Integer) map.get("orderId");
        int i = orderService.deleteOrder(orderId);
        return BaseRespVo.success("成功");
    }

    /**
     * 确认收货
     * @return
     */
    @RequestMapping("confirm")
    public BaseRespVo confirm(@RequestBody Map map){
        Integer orderId = (Integer) map.get("orderId");
        int i = orderService.updateOrderConfirm(orderId);
        return BaseRespVo.success(null);
    }

    /**
     * 待评价商品信息
     * @return
     */
    @RequestMapping("goods")
    public BaseRespVo goods(Integer orderId, Integer goodsId){
        return BaseRespVo.success(orderService.queryOrderGoods(orderId, goodsId));
    }

    /**
     * 评价订单商品信息
     * @return
     */
    @RequestMapping("comment")
    public BaseRespVo comment(@RequestBody Comment comment){
        Integer id = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        return BaseRespVo.success(commentService.insertComment(comment, id));
    }
}

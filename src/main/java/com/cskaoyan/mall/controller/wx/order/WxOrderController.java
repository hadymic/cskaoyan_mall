package com.cskaoyan.mall.controller.wx.order;

import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.service.mallmanager.OrderService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ordermanagement.SubmitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        /*String token = (String) SecurityUtils.getSubject().getPrincipal();*/
        Integer id = 1;
        return BaseRespVo.success(orderService.queryUserOrders(id,page,showType));
    }

    /**
     * 提交订单
     * @return
     */
    @RequestMapping("submit")
    public BaseRespVo submit(@RequestBody SubmitVo submitVo){
        int id = 1;
        return BaseRespVo.success(orderService.insertOrder(id,submitVo));
    }

    /**
     * 订单的预定支付会话
     * @return
     */
    @RequestMapping("prepay")
    public BaseRespVo prepay(@RequestBody Integer orderId){
        int i = orderService.updatePrepay(orderId);
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
    public BaseRespVo cancel(){
        return BaseRespVo.success(null);
    }

    /**
     * 退款取消订单
     * @return
     */
    @RequestMapping("refund")
    public BaseRespVo refund(){
        return BaseRespVo.success(null);
    }

    /**
     * 删除订单
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo delete(){
        return BaseRespVo.success(null);
    }

    /**
     * 确认收货
     * @return
     */
    @RequestMapping("confirm")
    public BaseRespVo confirm(){
        return BaseRespVo.success(null);
    }

    /**
     * 待评价商品信息
     * @return
     */
    @RequestMapping("goods")
    public BaseRespVo goods(){
        return BaseRespVo.success(null);
    }

    /**
     * 评价订单商品信息
     * @return
     */
    @RequestMapping("comment")
    public BaseRespVo comment(){
        return BaseRespVo.success(null);
    }
}

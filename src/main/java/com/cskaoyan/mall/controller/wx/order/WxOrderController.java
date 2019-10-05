package com.cskaoyan.mall.controller.wx.order;

import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.service.mallmanager.OrderService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    public BaseRespVo orderList(Integer showType, Integer page, Integer size){
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        Page pageBean = new Page(page,size,"add_time","desc");
        return BaseRespVo.success(orderService.queryUserOrders(token,pageBean,showType));
    }

    /**
     * 提交订单
     * @return
     */
    @RequestMapping("submit")
    public BaseRespVo submit(){
        return BaseRespVo.success(null);
    }

    /**
     * 订单的预定支付会话
     * @return
     */
    @RequestMapping("prepay")
    public BaseRespVo prepay(){
        return BaseRespVo.success(null);
    }

    /**
     * 订单详情
     * @return
     */
    @RequestMapping("detail")
    public BaseRespVo detail(){
        return BaseRespVo.success(null);
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

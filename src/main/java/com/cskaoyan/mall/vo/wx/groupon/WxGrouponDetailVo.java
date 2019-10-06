package com.cskaoyan.mall.vo.wx.groupon;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.vo.ordermanagement.OrderInfo;

import java.util.List;

/**
 * author Zeng-jz
 */
public class WxGrouponDetailVo {

    private WxUserVo creator;
    private Groupon groupon;
    private List<WxUserVo> joiners;
    private List<OrderGoods> orderGoods;
    private OrderInfo orderInfo;
    private GrouponRules rules;
    private int linkGrouponId;

    public int getLinkGrouponId() {
        return linkGrouponId;
    }

    public void setLinkGrouponId(int linkGrouponId) {
        this.linkGrouponId = linkGrouponId;
    }

    public WxUserVo getCreator() {
        return creator;
    }

    public void setCreator(WxUserVo creator) {
        this.creator = creator;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    public List<WxUserVo> getJoiners() {
        return joiners;
    }

    public void setJoiners(List<WxUserVo> joiners) {
        this.joiners = joiners;
    }

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public GrouponRules getRules() {
        return rules;
    }

    public void setRules(GrouponRules rules) {
        this.rules = rules;
    }
}

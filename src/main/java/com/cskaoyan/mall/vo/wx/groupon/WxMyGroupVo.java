package com.cskaoyan.mall.vo.wx.groupon;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.vo.ordermanagement.HandleOption;

import java.util.List;

/**
 * author Zeng-jz
 */
public class WxMyGroupVo {

    private double actualPrice;
    private String creator;
    private List<OrderGoods> goodsList;
    private Groupon groupon;
    private HandleOption handleOption;
    /**
     * id -→ groupon_id
     */
    private int id;
    private boolean isCreator;
    private int joinerCount;
    private int orderId;
    private String orderSn;
    private String orderStatusText;
    private GrouponRules rules;
    private int statusCode;
    private int rulesId;
    private int userId;

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<OrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<OrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    public HandleOption getHandleOption() {
        return handleOption;
    }

    public void setHandleOption(HandleOption handleOption) {
        this.handleOption = handleOption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsCreator() {
        return isCreator;
    }

    public void setIsCreator(boolean creator) {
        isCreator = creator;
    }

    public int getJoinerCount() {
        return joinerCount;
    }

    public void setJoinerCount(int joinerCount) {
        this.joinerCount = joinerCount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderStatusText() {
        return orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }

    public GrouponRules getRules() {
        return rules;
    }

    public void setRules(GrouponRules rules) {
        this.rules = rules;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getRulesId() {
        return rulesId;
    }

    public void setRulesId(int rulesId) {
        this.rulesId = rulesId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

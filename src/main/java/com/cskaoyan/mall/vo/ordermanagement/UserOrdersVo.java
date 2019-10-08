package com.cskaoyan.mall.vo.ordermanagement;

import com.cskaoyan.mall.bean.Groupon;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author jszza
 */
public class UserOrdersVo {
    private Integer id;
    private boolean isGroupin;
    private Groupon groupon;
    private String  orderSn;
    private Integer orderStatus;
    private String orderStatusText;
    private BigDecimal actualPrice;
    private List<UserOrderGoods> goodsList;
    private HandleOption handleOption;
    private Short comments;

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getIsGroupin() {
        return isGroupin;
    }

    public void setIsGroupin(boolean isGroupin) {
        this.isGroupin = isGroupin;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusText() {
        return orderStatusText;
    }

    public void setOrderStatusText(String orderStatusText) {
        this.orderStatusText = orderStatusText;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public List<UserOrderGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<UserOrderGoods> goodsList) {
        this.goodsList = goodsList;
    }

    public HandleOption getHandleOption() {
        return handleOption;
    }

    public void setHandleOption(HandleOption handleOption) {
        this.handleOption = handleOption;
    }

    public Short getComments() {
        return comments;
    }

    public void setComments(Short comments) {
        this.comments = comments;
    }
}

package com.cskaoyan.mall.vo.ordermanagement;

import java.math.BigDecimal;

/**
 * @author jszza
 */
public class RefundVo {
    private Integer orderId;
    private BigDecimal refundMoney;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }
}

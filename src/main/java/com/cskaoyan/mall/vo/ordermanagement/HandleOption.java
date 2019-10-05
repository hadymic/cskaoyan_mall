package com.cskaoyan.mall.vo.ordermanagement;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author jszza
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum HandleOption {
    // 未付款
    UNPAID(101, "未付款", true, false, false, false, true, false, false),
    // 用户取消
    CANCELED(102, "已取消", false, false, false, true, false, false, false),
    // 系统取消
    CANCELLATION(103, "已取消（系统）", false, false, false, true, false, false, false),
    // 已付款
    PAID(201, "已付款", false, false, false, false, false, false, true),
    // 申请退款
    REFUND(202, "申请退款", false, false, false, false, false, false, false),
    // 已退款
    REFUNDED(203, "已退款", false, false, false, true, false, false, false),
    // 已发货
    SHIP(301, "已发货", false, false, true, false, false, false, false),
    // 用户收货
    RECEIVE(401, "已收货", false, true, false, true, false, true, false),
    // 系统收货
    RECEIPT(402, "已收货（系统）", false, true, false, true, false, true, false);

    private int code;
    private String statusText;
    private boolean cancel;
    private boolean comment;
    private boolean confirm;
    private boolean delete;
    private boolean pay;
    private boolean rebuy;
    private boolean refund;

    HandleOption(int code, String statusText, boolean cancel, boolean comment, boolean confirm, boolean delete, boolean pay, boolean rebuy, boolean refund) {
        this.code = code;
        this.statusText = statusText;
        this.cancel = cancel;
        this.comment = comment;
        this.confirm = confirm;
        this.delete = delete;
        this.pay = pay;
        this.rebuy = rebuy;
        this.refund = refund;
    }

    public static HandleOption get(int code, boolean comment) {
        HandleOption handleOption = null;
        if (code == UNPAID.getCode()) {
            handleOption = UNPAID;
        } else if (code == CANCELED.getCode()) {
            handleOption = CANCELED;
        } else if (code == PAID.getCode()) {
            handleOption = PAID;
        } else if (code == REFUND.getCode()) {
            handleOption = REFUND;
        } else if (code == REFUNDED.getCode()) {
            handleOption = REFUNDED;
        } else if (code == SHIP.getCode()) {
            handleOption = SHIP;
        } else if (code == RECEIVE.getCode()) {
            RECEIVE.setComment(comment);
            handleOption = RECEIVE;
        } else if (code == RECEIPT.getCode()) {
            RECEIPT.setComment(comment);
            handleOption = RECEIPT;
        } else {
            handleOption = CANCELLATION;
        }
        return handleOption;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isConfirm() {
        return confirm;
    }

    public void setConfirm(boolean confirm) {
        this.confirm = confirm;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isPay() {
        return pay;
    }

    public void setPay(boolean pay) {
        this.pay = pay;
    }

    public boolean isRebuy() {
        return rebuy;
    }

    public void setRebuy(boolean rebuy) {
        this.rebuy = rebuy;
    }

    public boolean isRefund() {
        return refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }
}

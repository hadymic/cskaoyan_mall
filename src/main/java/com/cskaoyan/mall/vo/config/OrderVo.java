package com.cskaoyan.mall.vo.config;

public class OrderVo {
    String cskaoyan_mall_order_unconfirm;
    String cskaoyan_mall_order_unpaid;
    String cskaoyan_mall_order_comment;

    public String getCskaoyan_mall_order_comment() {
        return cskaoyan_mall_order_comment;
    }

    public OrderVo setCskaoyan_mall_order_comment(String cskaoyan_mall_order_comment) {
        this.cskaoyan_mall_order_comment = cskaoyan_mall_order_comment;
        return this;
    }

    public String getCskaoyan_mall_order_unconfirm() {
        return cskaoyan_mall_order_unconfirm;
    }

    public OrderVo setCskaoyan_mall_order_unconfirm(String cskaoyan_mall_order_unconfirm) {
        this.cskaoyan_mall_order_unconfirm = cskaoyan_mall_order_unconfirm;
        return this;
    }

    public String getCskaoyan_mall_order_unpaid() {
        return cskaoyan_mall_order_unpaid;
    }

    public OrderVo setCskaoyan_mall_order_unpaid(String cskaoyan_mall_order_unpaid) {
        this.cskaoyan_mall_order_unpaid = cskaoyan_mall_order_unpaid;
        return this;
    }

}

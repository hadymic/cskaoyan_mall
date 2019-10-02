package com.cskaoyan.mall.vo.statisticalform;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StatOrderGoodsVo {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date day;
    private int orders;
    private int products;
    private double amount;

    @Override
    public String toString() {
        return "StatOrderGoodsVo{" +
                "day=" + day +
                ", orders=" + orders +
                ", products=" + products +
                ", amount=" + amount +
                '}';
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getProducts() {
        return products;
    }

    public void setProducts(int products) {
        this.products = products;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

package com.cskaoyan.mall.vo.statisticalform;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StatOrderVo {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date day;
    private int orders;
    private double amount;
    private int customers;
    private double pcr;

    public StatOrderVo() {
    }

    public StatOrderVo(Date day, int orders, int amount, int customers, int pcr) {
        this.day = day;
        this.orders = orders;
        this.amount = amount;
        this.customers = customers;
        this.pcr = pcr;
    }

    @Override
    public String toString() {
        return "StatOrderVo{" +
                "day=" + day +
                ", orders=" + orders +
                ", amount=" + amount +
                ", customers=" + customers +
                ", pcr=" + pcr +
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }

    public double getPcr() {
        return pcr;
    }

    public void setPcr(double pcr) {
        this.pcr = pcr;
    }
}

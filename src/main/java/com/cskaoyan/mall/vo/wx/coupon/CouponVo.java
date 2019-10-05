package com.cskaoyan.mall.vo.wx.coupon;

import com.cskaoyan.mall.bean.Coupon;

import java.util.List;

public class CouponVo {
    List<Coupon> data;
    Integer count;

    public List<Coupon> getData() {
        return data;
    }

    public void setData(List<Coupon> data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

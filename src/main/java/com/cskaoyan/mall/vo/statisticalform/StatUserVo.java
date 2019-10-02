package com.cskaoyan.mall.vo.statisticalform;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StatUserVo {

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date day;
    private int users;

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getUsers() {
        return users;
    }

    public void setUsers(int users) {
        this.users = users;
    }
}

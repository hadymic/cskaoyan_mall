package com.cskaoyan.mall.util;

import java.util.List;

/**
 * 前台分页列表封装类
 *
 * @author hadymic
 */
public class WxListBean<T> {
    private List<T> data;
    private long count;

    public WxListBean(List<T> data, long count) {
        this.data = data;
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}

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
    private Integer totalPages;

    public WxListBean(List<T> data, long count, Integer totalPages) {
        this.data = data;
        this.count = count;
        this.totalPages = totalPages;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
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

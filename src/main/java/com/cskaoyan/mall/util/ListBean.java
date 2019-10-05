package com.cskaoyan.mall.util;

import java.util.List;

/**
 * 分页列表封装类
 *
 * @author hadymic
 */
public class ListBean<T> {
    private List<T> items;
    private long total;
    private long count;

    public ListBean() {
    }

    public ListBean(List<T> items, long total) {
        this.items = items;
        this.total = total;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<T> getItems() {
        return items;
    }

    public ListBean<T> setItems(List<T> items) {
        this.items = items;
        return this;
    }

    public long getTotal() {
        return total;
    }



    public void setTotal(long total) {
        this.total = total;
    }
}

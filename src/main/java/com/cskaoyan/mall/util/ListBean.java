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

    public ListBean(List<T> items, long total) {
        this.items = items;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}

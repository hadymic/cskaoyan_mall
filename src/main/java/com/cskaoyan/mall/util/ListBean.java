package com.cskaoyan.mall.util;

import java.util.List;

/**
 * 分页列表封装类
 *
 * @author hadymic
 */
public class ListBean<T> {
    private List<T> items;
    private int total;

    public ListBean(List<T> items, int total) {
        this.items = items;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public ListBean<T> setItems(List<T> items) {
        this.items = items;
        return this;
    }

    public int getTotal() {
        return total;
    }

    public ListBean<T> setTotal(int total) {
        this.total = total;
        return this;
    }
}

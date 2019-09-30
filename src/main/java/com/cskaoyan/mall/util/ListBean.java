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

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

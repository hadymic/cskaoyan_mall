package com.cskaoyan.mall.vo.ordermanagement;

import java.util.List;

/**
 * 分页列表封装类
 *
 * @author hadymic
 */
public class ListBeanForOrder<T> {
    private List<T> items;
    private long count;
    private int totalPages;

    public ListBeanForOrder(List<T> items, long count, int totalPages) {
        this.items = items;
        this.count = count;
        this.totalPages = totalPages;
    }

    public List<T> getItems() {
        return items;
    }

    public ListBeanForOrder<T> setItems(List<T> items) {
        this.items = items;
        return this;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

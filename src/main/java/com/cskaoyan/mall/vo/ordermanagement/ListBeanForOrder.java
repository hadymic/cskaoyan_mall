package com.cskaoyan.mall.vo.ordermanagement;

import java.util.List;


/**
 * @author jszza
 */
public class ListBeanForOrder<T> {
    private List<T> data;
    private long count;
    private int totalPages;

    public ListBeanForOrder(List<T> data, long count, int totalPages) {
        this.data = data;
        this.count = count;
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

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

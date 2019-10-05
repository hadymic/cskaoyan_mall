package com.cskaoyan.mall.util;

import java.util.List;

/**
 * 前台分页列表封装类
 *
 * @author hadymic
 */
public class WxFootPrintListBean<T> {
    private List<T> footprintList;
    private long totalPages;

    public WxFootPrintListBean(List<T> footprintList, long totalPages) {
        this.footprintList = footprintList;
        this.totalPages = totalPages;
    }

    public List<T> getFootprintList() {
        return footprintList;
    }

    public void setFootprintList(List<T> footprintList) {
        this.footprintList = footprintList;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}

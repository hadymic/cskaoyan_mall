package com.cskaoyan.mall.vo.wx.collect;

import java.util.List;

/**
 * @author jszza
 */
public class CollectResultVo<T> {
    private List<T> collectList;
    private Integer totalPages;

    public CollectResultVo(List<T> collectList, Integer totalPages) {
        this.collectList = collectList;
        this.totalPages = totalPages;
    }

    public List<T> getCollectList() {
        return collectList;
    }

    public void setCollectList(List<T> collectList) {
        this.collectList = collectList;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }
}

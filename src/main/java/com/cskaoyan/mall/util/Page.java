package com.cskaoyan.mall.util;

/**
 * 分页类
 *
 * @author hadymic
 */
public class Page {
    private int page;
    private int limit;
    private String sort;
    private String order;
    private String username;
    private String id;
    private String mobile;
    private String name;
    private String userId;
    private String valueId;
    private String goodsId;
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public Page setKeyword(String keyword) {
        this.keyword = keyword;
        return this;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public Page setGoodsId(String goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public String getValueId() { return valueId; }

    public Page setValueId(String valueId) { this.valueId = valueId;return this; }

    public String getUserId() { return userId; }

    public Page setUserId(String userId) { this.userId = userId;return this; }

    public String getName() { return name; }

    public Page setName(String name) { this.name = name;return this; }

    public String getMobile() { return mobile; }

    public Page setMobile(String mobile) { this.mobile = mobile;return this; }
    public String getId() {
        return id;
    }

    public Page setId(String id) { this.id = id;return this; }

    public String getUsername() {
        return username;
    }

    public Page setUsername(String username) { this.username = username;return this; }

    public Page(int page, int limit, String sort, String order) {
        this.page = page;
        this.limit = limit;
        this.sort = sort;
        this.order = order;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

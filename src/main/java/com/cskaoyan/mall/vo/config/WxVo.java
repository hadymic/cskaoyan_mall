package com.cskaoyan.mall.vo.config;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class WxVo {
    @NotEmpty
    String cskaoyan_mall_wx_catlog_goods;
    @NotEmpty
    String cskaoyan_mall_wx_catlog_list;
    @NotEmpty
    String cskaoyan_mall_wx_index_brand;;
    @NotEmpty
    String cskaoyan_mall_wx_index_hot;
    @NotEmpty
    String cskaoyan_mall_wx_index_new;
    @NotEmpty
    String cskaoyan_mall_wx_index_topic;
    @NotNull
    boolean cskaoyan_mall_wx_share;

    public String getCskaoyan_mall_wx_catlog_goods() {
        return cskaoyan_mall_wx_catlog_goods;
    }

    public WxVo setCskaoyan_mall_wx_catlog_goods(String cskaoyan_mall_wx_catlog_goods) {
        this.cskaoyan_mall_wx_catlog_goods = cskaoyan_mall_wx_catlog_goods.trim();
        return this;
    }

    public String getCskaoyan_mall_wx_catlog_list() {
        return cskaoyan_mall_wx_catlog_list;
    }

    public WxVo setCskaoyan_mall_wx_catlog_list(String cskaoyan_mall_wx_catlog_list) {
        this.cskaoyan_mall_wx_catlog_list = cskaoyan_mall_wx_catlog_list.trim();
        return this;
    }

    public String getCskaoyan_mall_wx_index_brand() {
        return cskaoyan_mall_wx_index_brand;
    }

    public WxVo setCskaoyan_mall_wx_index_brand(String cskaoyan_mall_wx_index_brand) {
        this.cskaoyan_mall_wx_index_brand = cskaoyan_mall_wx_index_brand.trim();
        return this;
    }

    public String getCskaoyan_mall_wx_index_hot() {
        return cskaoyan_mall_wx_index_hot;
    }

    public WxVo setCskaoyan_mall_wx_index_hot(String cskaoyan_mall_wx_index_hot) {
        this.cskaoyan_mall_wx_index_hot = cskaoyan_mall_wx_index_hot.trim();
        return this;
    }

    public String getCskaoyan_mall_wx_index_new() {
        return cskaoyan_mall_wx_index_new;
    }

    public WxVo setCskaoyan_mall_wx_index_new(String cskaoyan_mall_wx_index_new) {
        this.cskaoyan_mall_wx_index_new = cskaoyan_mall_wx_index_new.trim();
        return this;
    }

    public String getCskaoyan_mall_wx_index_topic() {
        return cskaoyan_mall_wx_index_topic;
    }

    public WxVo setCskaoyan_mall_wx_index_topic(String cskaoyan_mall_wx_index_topic) {
        this.cskaoyan_mall_wx_index_topic = cskaoyan_mall_wx_index_topic.trim();
        return this;
    }

    public boolean isCskaoyan_mall_wx_share() {
        return cskaoyan_mall_wx_share;
    }

    public WxVo setCskaoyan_mall_wx_share(boolean cskaoyan_mall_wx_share) {
        this.cskaoyan_mall_wx_share = cskaoyan_mall_wx_share;
        return this;
    }
    //判断传入的参数是否为空字符串
    public  boolean nonVoid(){
        if (cskaoyan_mall_wx_catlog_goods.equals("")||
                cskaoyan_mall_wx_catlog_list.equals("")||
                cskaoyan_mall_wx_index_brand.equals("")||
                cskaoyan_mall_wx_index_hot.equals("")||
                cskaoyan_mall_wx_index_new.equals("")||
                cskaoyan_mall_wx_index_topic.equals(""))
            return false;
        return true;

    }
}

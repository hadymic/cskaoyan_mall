package com.cskaoyan.mall.vo.wx.goodsmanagement;

import com.cskaoyan.mall.bean.*;

import java.util.List;
import java.util.Map;

/**
 * wx商品详情
 * @author stark_h
 */
public class WxGoodsDetailVo {
    private boolean userHasCollect;
    private String shareImage;
    private CommentVo comment;
    private Brand brand;
    private Map<String, Object> specificationList;
    private List<Groupon> groupons;

    private List<Issue> Issues;

    private List<GoodsAttribute> attributes;

    private List<GoodsProduct> productList;

    private Goods info;

    public WxGoodsDetailVo() {
    }

    public WxGoodsDetailVo(boolean userHasCollect, String shareImage, CommentVo comment, Brand brand, Map<String, Object> specificationList, List<Groupon> groupons, List<Issue> issues, List<GoodsAttribute> attributes, List<GoodsProduct> productList, Goods info) {
        this.userHasCollect = userHasCollect;
        this.shareImage = shareImage;
        this.comment = comment;
        this.brand = brand;
        this.specificationList = specificationList;
        this.groupons = groupons;
        Issues = issues;
        this.attributes = attributes;
        this.productList = productList;
        this.info = info;
    }

    public boolean isUserHasCollect() {
        return userHasCollect;
    }

    public void setUserHasCollect(boolean userHasCollect) {
        this.userHasCollect = userHasCollect;
    }

    public String getShareImage() {
        return shareImage;
    }

    public void setShareImage(String shareImage) {
        this.shareImage = shareImage;
    }

    public CommentVo getComment() {
        return comment;
    }

    public void setComment(CommentVo comment) {
        this.comment = comment;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Map<String, Object> getSpecificationList() {
        return specificationList;
    }

    public void setSpecificationList(Map<String, Object> specificationList) {
        this.specificationList = specificationList;
    }

    public List<Groupon> getGroupons() {
        return groupons;
    }

    public void setGroupons(List<Groupon> groupons) {
        this.groupons = groupons;
    }

    public List<Issue> getIssues() {
        return Issues;
    }

    public void setIssues(List<Issue> issues) {
        Issues = issues;
    }

    public List<GoodsAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<GoodsAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<GoodsProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<GoodsProduct> productList) {
        this.productList = productList;
    }

    public Goods getInfo() {
        return info;
    }

    public void setInfo(Goods info) {
        this.info = info;
    }
}

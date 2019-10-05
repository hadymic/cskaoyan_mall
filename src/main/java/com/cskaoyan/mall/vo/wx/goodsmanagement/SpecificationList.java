package com.cskaoyan.mall.vo.wx.goodsmanagement;

import com.cskaoyan.mall.bean.GoodsSpecification;

import java.util.List;

public class SpecificationList {
    private String name;

    private List<GoodsSpecification> valueList;

    public SpecificationList() {
    }

    public SpecificationList(String name, List<GoodsSpecification> valueList) {
        this.name = name;
        this.valueList = valueList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GoodsSpecification> getValueList() {
        return valueList;
    }

    public void setValueList(List<GoodsSpecification> valueList) {
        this.valueList = valueList;
    }
}

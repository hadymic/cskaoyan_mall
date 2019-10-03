package com.cskaoyan.mall.vo.goodsMangement;


import com.cskaoyan.mall.vo.BaseValueLabel;

import java.util.List;

public class CategoryList {
    private Integer value;

    private String label;

    private List<BaseValueLabel> children;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<BaseValueLabel> getChildren() {
        return children;
    }

    public void setChildren(List<BaseValueLabel> children) {
        this.children = children;
    }

    public CategoryList() {
    }

    public CategoryList(Integer value, String label, List<BaseValueLabel> children) {
        this.value = value;
        this.label = label;
        this.children = children;
    }
}

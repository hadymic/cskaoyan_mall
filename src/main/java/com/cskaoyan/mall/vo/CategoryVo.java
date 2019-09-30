package com.cskaoyan.mall.vo;

public class CategoryVo {

    private int value;
    private String label;

    public CategoryVo() {
    }

    public CategoryVo(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

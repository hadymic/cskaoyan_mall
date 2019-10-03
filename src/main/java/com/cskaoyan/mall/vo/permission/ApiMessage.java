package com.cskaoyan.mall.vo.permission;

import java.util.List;

public class ApiMessage {
    List<Api> children;
    String id;
    String label;

    public List<Api> getChildren() {
        return children;
    }

    public void setChildren(List<Api> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

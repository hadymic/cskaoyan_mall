package com.cskaoyan.mall.vo.permission;

import java.util.List;

public class SystemPermission {
    List<ApiPermission> children;
    String id;
    String label;

    public List<ApiPermission> getChildren() {
        return children;
    }

    public void setChildren(List<ApiPermission> children) {
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

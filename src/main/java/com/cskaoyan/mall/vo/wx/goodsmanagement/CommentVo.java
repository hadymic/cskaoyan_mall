package com.cskaoyan.mall.vo.wx.goodsmanagement;

import com.cskaoyan.mall.bean.Comment;

import java.util.List;

public class CommentVo {
    private List<Comment> data;
    private int count;

    public CommentVo() {
    }

    public CommentVo(List<Comment> data, int count) {
        this.data = data;
        this.count = count;
    }

    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}


package com.cskaoyan.mall.vo.wx.comment;

import com.cskaoyan.mall.vo.wx.groupon.WxUserVo;

import java.util.Date;


public class WxCommentVo {
    private WxUserVo userInfo;

    private Date addTime;

    private String[] picList;

    private String content;

    public WxCommentVo() {
    }

    public WxCommentVo(WxUserVo userInfo, Date addTime, String[] picList, String content) {
        this.userInfo = userInfo;
        this.addTime = addTime;
        this.picList = picList;
        this.content = content;
    }

    public WxUserVo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(WxUserVo userInfo) {
        this.userInfo = userInfo;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String[] getPicList() {
        return picList;
    }

    public void setPicList(String[] picList) {
        this.picList = picList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

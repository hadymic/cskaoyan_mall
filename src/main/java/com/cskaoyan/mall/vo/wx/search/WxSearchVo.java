package com.cskaoyan.mall.vo.wx.search;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.SearchHistory;

import java.util.List;

/**
 * author Zeng-jz
 */
public class WxSearchVo {

    private Keyword defaultKeyword;
    private List<SearchHistory> historyKeywordList;
    private List<Keyword> hotKeywordList;

    public WxSearchVo(Keyword defaultKeyword, List<SearchHistory> historyKeywordList, List<Keyword> hotKeywordList) {
        this.defaultKeyword = defaultKeyword;
        this.historyKeywordList = historyKeywordList;
        this.hotKeywordList = hotKeywordList;
    }

    public Keyword getDefaultKeyword() {
        return defaultKeyword;
    }

    public void setDefaultKeyword(Keyword defaultKeyword) {
        this.defaultKeyword = defaultKeyword;
    }

    public List<SearchHistory> getHistoryKeywordList() {
        return historyKeywordList;
    }

    public void setHistoryKeywordList(List<SearchHistory> historyKeywordList) {
        this.historyKeywordList = historyKeywordList;
    }

    public List<Keyword> getHotKeywordList() {
        return hotKeywordList;
    }

    public void setHotKeywordList(List<Keyword> hotKeywordList) {
        this.hotKeywordList = hotKeywordList;
    }
}

package com.cskaoyan.mall.service.wx.search.impl;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.cskaoyan.mall.mapper.SearchHistoryMapper;
import com.cskaoyan.mall.service.wx.search.WxSearchService;
import com.cskaoyan.mall.vo.wx.search.WxSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WxSearchServiceImpl implements WxSearchService {

    @Autowired
    KeywordMapper keywordMapper;
    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Override
    public WxSearchVo index() {
        int userId = 1;
        List<Keyword> defaultKeywordList = keywordMapper.selectDefaultKeyword();
        List<SearchHistory> historyKeywordList = searchHistoryMapper.selectHistoryKeywordList(userId);
        List<Keyword> hotKeywordList = keywordMapper.selectHotKeyword();
        return new WxSearchVo(defaultKeywordList.get(0), historyKeywordList, hotKeywordList);
    }

    /**
     * 搜索帮助
     * @param keyword
     * @return
     */
    @Override
    public List<String> selectHotByKerword(String keyword) {
        List<Keyword> hotKeywordList = keywordMapper.selectHotByKeyword("%" + keyword + "%");
        List<String> data = new ArrayList<>();
        for (Keyword hotKeyword : hotKeywordList) {
            data.add(hotKeyword.getKeyword());
        }
        return data;
    }

    @Override
    public boolean deleltedHistoryKeyword() {
        int userId = 1;
        int i = searchHistoryMapper.deleteByUserId(userId);
        return i != 0;
    }
}

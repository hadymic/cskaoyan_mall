package com.cskaoyan.mall.service.history.impl;

import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.mapper.SearchHistoryMapper;
import com.cskaoyan.mall.service.history.WxHistoryService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WxHistoryServiceImpl implements WxHistoryService {
    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Override
    public void insertHistory(String keyword) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        if (userId == null) return;
        int count = searchHistoryMapper.selectUniqueHistory(userId, keyword);
        if (count < 1) {
            Date date = new Date();
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setKeyword(keyword);
            searchHistory.setUserId(userId);
            searchHistory.setAddTime(date);
            searchHistory.setUpdateTime(date);
            searchHistoryMapper.insertSelective(searchHistory);
        }
    }
}

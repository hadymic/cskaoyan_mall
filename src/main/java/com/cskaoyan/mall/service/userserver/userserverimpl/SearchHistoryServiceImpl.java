package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.mapper.SearchHistoryMapper;
import com.cskaoyan.mall.service.userserver.SearchHistoryService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchHistoryServiceImpl implements SearchHistoryService {
    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    @Override
    public ListBean getSearchHistoryList(Page utipage) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        String userId = utipage.getUserId();
        String keyword = utipage.getKeyword();
        if (userId=="") userId = null;
        if (keyword=="") keyword = null;
        List<SearchHistory> searchHistories = searchHistoryMapper.selectByUserIdAndKeyword(userId,keyword);
//        PageInfo<SearchHistory> pageInfo = new PageInfo<SearchHistory>(searchHistories);
//        long total = pageInfo.getTotal();
//        ListBean listBean = new ListBean(searchHistories,total);
//        return listBean;
        return PageUtils.page(searchHistories);
        //return null;
    }
}

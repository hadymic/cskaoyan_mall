package com.cskaoyan.mall.service.userserver;

import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

import java.util.List;

public interface SearchHistoryService {
    ListBean getSearchHistoryList(Page utipage,String userId,String keyword);
}

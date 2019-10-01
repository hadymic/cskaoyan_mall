package com.cskaoyan.mall.service.userserver;

import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

import java.util.List;

public interface SearchHistoryServer {
    ListBean getSearchHistoryList(Page utipage);
}

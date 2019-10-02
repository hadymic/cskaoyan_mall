package com.cskaoyan.mall.service.userserver;

import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

public interface FeedbackService {
    ListBean getFeedbackList(Page utipage,String id,String username);
}

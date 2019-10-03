package com.cskaoyan.mall.service.userserver;

import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

public interface CollectService {
    ListBean getCollectList(Page utipage,String userId,String valueId);
}

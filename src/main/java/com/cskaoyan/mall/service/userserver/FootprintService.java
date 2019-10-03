package com.cskaoyan.mall.service.userserver;

import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

public interface FootprintService {
    ListBean getFootprintList(Page utipage,String userId,String goodsId);
}

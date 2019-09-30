package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;


public interface GoodsService {
    ListBean queryGoods(Page page);
}

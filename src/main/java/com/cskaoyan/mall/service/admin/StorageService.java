package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

public interface StorageService {
    ListBean<Storage> queryStorage(String key, String name, Page page);
}

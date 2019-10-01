package com.cskaoyan.mall.service.admin.impl;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.service.admin.StorageService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageMapper storageMapper;
    @Override
    public ListBean<Storage> queryStorage(String key, String name, Page page) {
        PageUtils.startPage(page);
        List<Storage> storages = storageMapper.queryStorage(key,  name);
        return PageUtils.page(storages);
    }

    @Override
    public void delete(Storage storage) {
        int i = storageMapper.deleteStorage(storage.getId());


    }

    @Override
    public void update(Storage storage) {
        storageMapper.updateByPrimaryKey(storage);
    }
}

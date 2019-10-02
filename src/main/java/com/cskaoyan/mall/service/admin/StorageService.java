package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    ListBean<Storage> queryStorage(String key, String name, Page page);

    void delete(Storage storage);

    void update(Storage storage);

    Storage createPic(MultipartFile file) throws IOException;
}

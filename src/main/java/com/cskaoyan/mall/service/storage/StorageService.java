package com.cskaoyan.mall.service.storage;

import com.cskaoyan.mall.bean.Storage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    Storage createPic(MultipartFile file) throws IOException;
}

package com.cskaoyan.mall.controller.storage;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.config.MyFileConfig;
import com.cskaoyan.mall.service.storage.StorageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 文件上传
 *
 * @author hadymic
 */
@RestController
@RequestMapping("admin/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("create")
    public BaseRespVo createPic(MultipartFile file) throws IOException {
        Storage storage = storageService.createPic(file);
        return BaseRespVo.success(storage);
    }
}

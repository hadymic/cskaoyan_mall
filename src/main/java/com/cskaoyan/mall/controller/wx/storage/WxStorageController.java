package com.cskaoyan.mall.controller.wx.storage;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.admin.StorageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 微信上传图片
 */
@RestController
@RequestMapping("wx/storage")
public class WxStorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("upload")
    public BaseRespVo upload(MultipartFile file) throws IOException {
        Storage storage = storageService.createPic(file);
        return BaseRespVo.success(storage);
    }
}

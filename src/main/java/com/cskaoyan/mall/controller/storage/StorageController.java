package com.cskaoyan.mall.controller.storage;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 文件上传
 *
 * @author hadymic
 */
@RestController
@RequestMapping("admin/storage")
public class StorageController {

    @PostMapping("create")
    public BaseRespVo create() {
        Storage storage = new Storage();
        storage.setUrl("abc");
        return BaseRespVo.success(storage);
    }
}

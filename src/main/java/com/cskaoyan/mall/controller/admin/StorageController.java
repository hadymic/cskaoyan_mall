package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.admin.StorageService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * 对象存储
 */
@RestController
@RequestMapping("admin/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;

    /**
     * 对象存储
     *
     * @param key
     * @param name
     * @param page
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo storage(String key, String name, Page page) {
        ListBean<Storage> storages = storageService.queryStorage(key, name, page);
        return BaseRespVo.success(storages);
    }


    /**
     * 删除图片
     *
     * @param storage
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Storage storage) {
        storageService.delete(storage);
        return BaseRespVo.success(null);
    }

    /**
     * 修改图片信息
     *
     * @param storage
     * @return
     */
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Storage storage) {
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storageService.update(storage);
        return BaseRespVo.success(storage);

    }

    /**
     * 添加图片
     *
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("create")
    public BaseRespVo createPic(MultipartFile file) throws IOException {
        Storage storage = storageService.createPic(file);
        return BaseRespVo.success(storage);
    }
}

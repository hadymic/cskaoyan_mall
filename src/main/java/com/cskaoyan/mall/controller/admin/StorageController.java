package com.cskaoyan.mall.controller.admin;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.service.admin.StorageService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
    @Autowired
    StorageService  storageService;

    /**
     * 对象存储
     * @param key
     * @param name
     * @param page
     * @return
     */
            @RequestMapping("admin/storage/list")
    public BaseRespVo storage(String key, String name, Page page){
                ListBean<Storage> storages=storageService.queryStorage(key,name,page);
                return  BaseRespVo.success(storages);
            }
}

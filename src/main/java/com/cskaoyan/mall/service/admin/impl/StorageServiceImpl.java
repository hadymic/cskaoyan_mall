package com.cskaoyan.mall.service.admin.impl;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.config.MyFileConfig;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.service.admin.StorageService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private MyFileConfig myFileConfig;
    @Autowired
    private StorageMapper storageMapper;
@Autowired

    @Override
    public ListBean<Storage> queryStorage(String key, String name, Page page) {
        PageUtils.startPage(page);
        List<Storage> storages = storageMapper.queryStorage(key, name);
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

    @Override
    public Storage createPic(MultipartFile file) throws IOException {
        //文件名
        String fileName = file.getOriginalFilename();
        //获取散列地址
        fileName = myFileConfig.hashUUID(fileName);
        //最终存储位置
        File myFile = new File(myFileConfig.getCompleteTargetPath());
        if (!myFile.exists()) {
            myFile.mkdirs();
        }
        myFile = new File(myFile, fileName);
        //存储文件
        file.transferTo(myFile);

        //构建storage对象
        Storage storage = new Storage();
        //注意：存储的是相对路径
        storage.setUrl(myFileConfig.getHashPath() + fileName);
        storage.setName(file.getName());
        storage.setAddTime(new Date());
        storage.setDeleted(false);
        storage.setKey(file.getContentType().substring(0, file.getContentType().indexOf("/")));
        storage.setSize((int) file.getSize());
        storage.setType(file.getContentType());

        //数据库存储记录
        storageMapper.insertSelective(storage);
        //为了图片能够显示出来
        storage.setUrl(myFileConfig.addPicUrl("http://192.168.2.100:8081:")+myFileConfig.getCompleteNetPath() + fileName);
        return storage;
    }
}

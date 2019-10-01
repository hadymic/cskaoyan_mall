package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Storage;

import java.util.List;

public interface StorageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    int insertSelective(Storage record);

    Storage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

    List<Storage> queryStorage(String key, String name);

    int deleteStorage(Integer id);
}

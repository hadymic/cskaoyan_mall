package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.util.Page;
import org.apache.ibatis.annotations.Param;

public interface SystemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(System record);

    int insertSelective(System record);

    System selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(System record);

    int updateByPrimaryKey(System record);

    int updateByKeyNema(@Param("keyValue")String keyValue,
                        @Param("keyName") String keyName);
}

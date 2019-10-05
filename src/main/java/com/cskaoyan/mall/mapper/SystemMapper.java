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

    String selectByKeyName(@Param("keyName") String keyName);

<<<<<<< HEAD
=======
    /**
     * Zeng-jz: 根据id查询小程序配置
     * @param i
     * @return
     */
    int selectAppletsConfigById(@Param("id") int i);
>>>>>>> a5d835781a1a72701874be0d5baf951ae39078d0
}

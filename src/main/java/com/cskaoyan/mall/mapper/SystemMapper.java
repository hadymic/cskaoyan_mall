package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.wx.home.AppletsConfigVo;
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


    /**
     * Zeng-jz: 根据id查询小程序配置
     * @param i
     * @return
     */
    int selectAppletsConfigById(@Param("id") int i);


    /**
     * Zeng-jz: 查询最新的小程序配置
     * @return
     */
    AppletsConfigVo selectAppletsConfigs();

}

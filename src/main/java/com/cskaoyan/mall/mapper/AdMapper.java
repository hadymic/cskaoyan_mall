package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Ad;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Ad record);

    int insertSelective(Ad record);

    Ad selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Ad record);

    int updateByPrimaryKey(Ad record);

    List<Ad> queryAds(@Param("name") String name, @Param("content") String content);

    int insertSelectKey(Ad record);
}
package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Region;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer id);

    List<Region> selectAll();

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);
}

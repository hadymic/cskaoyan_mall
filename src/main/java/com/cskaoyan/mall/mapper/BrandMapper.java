package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.util.Page;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer id);

    List<Brand> selectAll(String id, String name);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);
}

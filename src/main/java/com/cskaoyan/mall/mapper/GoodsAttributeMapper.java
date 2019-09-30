package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsAttribute;

public interface GoodsAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAttribute record);

    int insertSelective(GoodsAttribute record);

    GoodsAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsAttribute record);

    int updateByPrimaryKey(GoodsAttribute record);
}
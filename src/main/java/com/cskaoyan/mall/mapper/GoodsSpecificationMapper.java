package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsSpecification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsSpecificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpecification record);

    int insertSelective(GoodsSpecification record);

    GoodsSpecification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsSpecification record);

    int updateByPrimaryKey(GoodsSpecification record);

    List<GoodsSpecification> selectSpecificationsByGoodsId(@Param("goodsId") int goodsId);
}

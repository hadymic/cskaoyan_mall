package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsSpecification;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author stark_h
 */
public interface GoodsSpecificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsSpecification record);

    int insertSelective(GoodsSpecification record);

    GoodsSpecification selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsSpecification record);

    int updateByPrimaryKey(GoodsSpecification record);

    /**
     * 根据goodsId查找对应Specification
     * @param goodsId
     * @return
     */
    List<GoodsSpecification> selectSpecificationsByGoodsId(@Param("goodsId") int goodsId);

    /**
     * 更新Specification
     * @param goodsId
     * @param date
     */
    void updateSpecificationDeleted(@Param("goodsId") int goodsId, @Param("date") Date date);
}

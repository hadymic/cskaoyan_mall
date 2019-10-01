package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsProduct record);

    int insertSelective(GoodsProduct record);

    GoodsProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsProduct record);

    int updateByPrimaryKey(GoodsProduct record);

    /**
     * @param goodsId
     * @return
     */
    List<GoodsProduct> selectProductsByGoodsId(@Param("goodsId") int goodsId);
}

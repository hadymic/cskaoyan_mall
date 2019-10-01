package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsAttribute;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface GoodsAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAttribute record);

    int insertSelective(GoodsAttribute record);

    GoodsAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsAttribute record);

    int updateByPrimaryKey(GoodsAttribute record);

    /**
     * @param goodsId
     * @return
     */
    List<GoodsAttribute> selectAttributesByGoodsId(@Param("goodsId")int goodsId);
}

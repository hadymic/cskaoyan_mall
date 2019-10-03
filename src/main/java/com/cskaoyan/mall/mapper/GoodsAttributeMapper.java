package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsAttribute;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


/**
 * @author stark_h
 */
public interface GoodsAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAttribute record);

    int insertSelective(GoodsAttribute record);

    GoodsAttribute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsAttribute record);

    int updateByPrimaryKey(GoodsAttribute record);

    /**
     * 根据goodsId查找Attribute
     * @param goodsId
     * @return
     */
    List<GoodsAttribute> selectAttributesByGoodsId(@Param("goodsId")Integer goodsId);

    /**
     * 更新attribute
     * @param goodsId
     * @param date
     */
    void updateAttributeDeleted(@Param("goodsId") int goodsId,@Param("date") Date date);
}

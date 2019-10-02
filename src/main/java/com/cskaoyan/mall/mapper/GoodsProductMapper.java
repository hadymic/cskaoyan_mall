package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface GoodsProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GoodsProduct record);

    int insertSelective(GoodsProduct record);

    GoodsProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GoodsProduct record);

    int updateByPrimaryKey(GoodsProduct record);

    /**
     * 根据product查找product
     * @param goodsId
     * @return
     */
    List<GoodsProduct> selectProductsByGoodsId(@Param("goodsId") int goodsId);

    /**
     *删除Product
     * @param goodsId
     * @param date
     */
    void updateProductDeleted(@Param("goodsId") int goodsId,@Param("date") Date date);

    /**
     * 根据spec和goodsId找到对应product更新
     * @param product
     */
    void updateProductBySpecAndGoodsId(@Param("product") GoodsProduct product);
}

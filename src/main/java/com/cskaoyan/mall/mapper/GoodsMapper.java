package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    /**根据(goodsSn 或者 name)不为空的话 查找商品
     * @author hjl
     * @return
     */
    List<Goods> selectGoodsByGoodsSnOrName(@Param("goods") Goods goods);

    int insertSelectKey(Goods record);
}

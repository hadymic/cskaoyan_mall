package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.util.Page;

import java.util.List;

public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);
    //显示商品分页
    List<Goods> selectGoodsList();
   //根据goodsSn 或者 name 查找商品
    List<Goods> selectGoodsByGoodsSnOrName(Goods goods);
}

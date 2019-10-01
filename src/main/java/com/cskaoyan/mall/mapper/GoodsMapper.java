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

    /**
     * @author hjl
     * @return  //显示商品分页
     */
    List<Goods> selectGoodsList();

    /**根据goodsSn 或者 name 查找商品
     * @author hjl
     * @return
     */
    List<Goods> selectGoodsByGoodsSnOrName(@Param("goods") Goods goods);
    //删除商品(实际将deleted 更新为1 )
    void deleteGoods(@Param("id") int id);
}

package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.vo.wx.footprint.FootPrintVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FootprintMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Footprint record);

    int insertSelective(Footprint record);

    Footprint selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Footprint record);

    int updateByPrimaryKey(Footprint record);

    List<Footprint> selectByUserIdAndGoodsId(@Param("userId") String userId,
                                             @Param("goodsId") String goodsid);

    List<FootPrintVo> queryFootPrint();

    //足迹去重
    int selectByIntUserIdAndGoodsId(@Param("userId") int userId,@Param("goodsId") int id);
}

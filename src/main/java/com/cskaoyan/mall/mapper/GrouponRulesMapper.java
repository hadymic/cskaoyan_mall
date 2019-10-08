package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.wx.home.GrouponVo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface GrouponRulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GrouponRules record);

    int insertSelective(GrouponRules record);

    GrouponRules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GrouponRules record);

    int updateByPrimaryKey(GrouponRules record);

    List<GrouponRules> queryGrouponRuless(@Param("goodsId") Integer goodsId);

    int insertSelectKey(GrouponRules record);

    /**
     * Zeng_jz: 查询时间倒序前五的优惠规则
     * @return
     */
    List<GrouponVo> selectTopFiveByTime();

    List<GrouponVo> selectAll();

    BigDecimal getDiscount(Integer goodsId);
}

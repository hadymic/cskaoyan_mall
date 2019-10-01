package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GrouponRules;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrouponRulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GrouponRules record);

    int insertSelective(GrouponRules record);

    GrouponRules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GrouponRules record);

    int updateByPrimaryKey(GrouponRules record);

    List<GrouponRules> queryGrouponRuless(@Param("goodsId") Integer goodsId);
}
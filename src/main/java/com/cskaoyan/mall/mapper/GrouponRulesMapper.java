package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GrouponRules;

public interface GrouponRulesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GrouponRules record);

    int insertSelective(GrouponRules record);

    GrouponRules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GrouponRules record);

    int updateByPrimaryKey(GrouponRules record);
}
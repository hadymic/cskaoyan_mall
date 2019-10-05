package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Groupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Groupon record);

    int insertSelective(Groupon record);

    Groupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Groupon record);

    int updateByPrimaryKey(Groupon record);

    List<Groupon> queryGrouponsByRuleId(@Param("ruleId") Integer ruleId);

    Groupon selectByUidAndGid(@Param("gid") int id, @Param("uid") int userId);
}

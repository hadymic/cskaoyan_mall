package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.vo.StatUserVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<StatUserVo> selectUsersByDay();
}

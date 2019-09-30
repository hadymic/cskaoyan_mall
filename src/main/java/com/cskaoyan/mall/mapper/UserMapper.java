package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> updataByNameAndId(@Param("username") String username,
                                 @Param("id") String id);
}

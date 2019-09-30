package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据order的userId查询user赋给orderDetail
     * @param id
     * @return
     */
    User queryUserForOrder(@Param("id") Integer id);
}

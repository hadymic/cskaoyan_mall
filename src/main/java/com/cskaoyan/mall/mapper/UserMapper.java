package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.User;
import org.apache.ibatis.annotations.Param;
import com.cskaoyan.mall.vo.StatUserVo;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * jszza:根据order的userId查询user赋给orderDetail
     * @param id
     * @return
     */
    User queryUserForOrder(@Param("id") Integer id);

    List<StatUserVo> selectUsersByDay();

    List<User> selectByNameAndMobile(@Param("username") String username,
                                 @Param("mobile") String mobile);

}

package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.User;
<<<<<<< HEAD
import com.cskaoyan.mall.vo.StatUserVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
=======
import org.apache.ibatis.annotations.Param;

import java.util.List;
>>>>>>> 7427413ab74fa9600b4dae2641a5300db68d68ec

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

<<<<<<< HEAD
    List<StatUserVo> selectUsersByDay();
=======
    List<User> selectByNameAndMobile(@Param("username") String username,
                                 @Param("mobile") String mobile);
>>>>>>> 7427413ab74fa9600b4dae2641a5300db68d68ec
}

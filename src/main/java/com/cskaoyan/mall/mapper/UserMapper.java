package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.User;


import com.cskaoyan.mall.vo.statisticalform.StatUserVo;

import java.util.List;


import com.cskaoyan.mall.vo.wx.groupon.WxMyGroupVo;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);


    /**
     * jszza:根据order的userId查询user赋给orderDetail
     *
     * @param id
     * @return
     */
    User queryUserForOrder(@Param("id") Integer id);


    List<StatUserVo> selectUsersByDay();

    List<User> selectByNameAndMobile(@Param("username") String username,
                                     @Param("mobile") String mobile);

    //根据用户名查询Id假设用户名为账号名且唯一
    Integer selectByNameGetId(@Param("username") String username);

    /**
     * 返回用户数量
     *
     * @return
     */
    Integer queryUserNumber();

    /**
     * 返回根据token获得user的id
     *
     * @param token
     * @return
     */
    Integer queryUserIdByToken(@Param("token") String token);

    /**
     * author: Zeng-jz
     *
     * @param userId
     * @return
     */
    List<WxMyGroupVo> selectWxGroupVoById(int userId);

    List<WxMyGroupVo> selectWxGroupVoJoinById(int userId);

    /**
     * 通过用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     * @author hadymic
     */
    User queryByUserNameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 通过用户名查询密码
     *
     * @param principal
     * @return
     */
    String queryPasswordByUsername(String principal);
}

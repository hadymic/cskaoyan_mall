package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    List<Admin> queryAdminsByLikeUsername(String username);

    Integer[] queryRoleIds(String username);

    String queryPasswordByUsername(@Param("username") String username);

    int deleteAdmin(Integer id);


    int  queryIsExist(String username,String password);


    /**
     * 修改管理员密码
     * @param oldPassword
     * @param newPassword
     * @param token
     * @return
     */
    int updatePassword(@Param("oldPassword") String oldPassword, @Param("newPassword")String newPassword, @Param("token")String token);

    List<String> queryPermissionsByUsername(@Param("username") String username);

    List<String> queryApisByUsername(@Param("username") String username);

    Admin queryAdminByUsername(String username);

}

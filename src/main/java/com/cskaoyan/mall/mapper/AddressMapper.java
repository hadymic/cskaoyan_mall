package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    List<User> selectByIdAndNameKey(@Param("userId") String userid,
                                    @Param("name") String name);

    List<Address> selectAddress(@Param("username") String username);

    Integer addProfile(@Param("address") Address address);

    Integer concealAddress(@Param("id") int id);

    Address selectAddressById(@Param("id")int id);

}

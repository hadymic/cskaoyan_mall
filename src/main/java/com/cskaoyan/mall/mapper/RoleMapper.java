package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.vo.CategoryVo;
import com.cskaoyan.mall.vo.goodsMangement.BaseValueLabel;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    List<Role> queryRole(String name);

    List<BaseValueLabel> roleOptions();

    int deleteRloe(Integer id);
}

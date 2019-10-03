package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.CategoryVo;
import com.cskaoyan.mall.vo.goodsMangement.BaseValueLabel;

import java.util.List;

public interface RoleService {
    ListBean<Role> queryRole(Page page, String name);

    List<BaseValueLabel>  roleOptions();

    int update(Role role);

    void delete(Role role);

    Role insertRole(Role role);
}

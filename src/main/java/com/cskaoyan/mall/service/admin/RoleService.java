package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.CategoryVo;

public interface RoleService {
    ListBean<Role> queryRole(Page page, String name);

    ListBean<CategoryVo> roleOptions();
}

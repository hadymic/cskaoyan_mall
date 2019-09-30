package com.cskaoyan.mall.service.role;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

public interface RoleService {
    ListBean<Role> queryRole(Page page, String name);
}

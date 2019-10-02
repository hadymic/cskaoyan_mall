package com.cskaoyan.mall.service.userserver;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

import java.util.List;

public interface UserManageService {
    ListBean getDispalyList(Page page,String username, String mobile);
}

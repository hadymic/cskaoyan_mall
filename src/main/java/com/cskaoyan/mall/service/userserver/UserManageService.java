package com.cskaoyan.mall.service.userserver;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.user.UserIndexVo;

import java.util.List;

public interface UserManageService {
    ListBean getDispalyList(Page page,String username, String mobile);

    /**
     * 获取用户的订单状态
     * @param token
     * @return
     */
    UserIndexVo queryUserOrders(String token);
}

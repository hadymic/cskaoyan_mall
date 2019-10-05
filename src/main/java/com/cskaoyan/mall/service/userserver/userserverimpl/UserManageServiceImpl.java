package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.userserver.UserManageService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.StringUtils;
import com.cskaoyan.mall.vo.user.UserIndexVo;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.page.PageMethod;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;
    @Override
    public ListBean getDispalyList(Page utipage,String username, String mobile) {
        PageHelper.startPage(utipage.getPage(),utipage.getLimit());
        username = StringUtils.addPercent(username);
        mobile = StringUtils.addPercent(mobile);
        List<User> users = userMapper.selectByNameAndMobile(username,mobile);
        return PageUtils.page(users);
    }

    @Override
    public UserIndexVo queryUserOrders(String token) {
        UserIndexVo userIndexVo = new UserIndexVo();
        Integer id = userMapper.queryUserIdByToken(token);
        userIndexVo.setUncomment(orderMapper.queryUserOrdersNumber(id,(short)401));
        userIndexVo.setUnpaid(orderMapper.queryUserOrdersNumber(id,(short)101));
        userIndexVo.setUnrecv(orderMapper.queryUserOrdersNumber(id,(short)301));
        userIndexVo.setUnship(orderMapper.queryUserOrdersNumber(id,(short)201));
        return userIndexVo;
    }

}

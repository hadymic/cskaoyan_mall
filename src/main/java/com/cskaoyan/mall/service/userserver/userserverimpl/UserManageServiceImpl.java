package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.userserver.UserManageService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.StringUtils;
import com.github.pagehelper.PageHelper;

import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserManageServiceImpl implements UserManageService {
    @Autowired
    UserMapper userMapper;
    @Override
    public ListBean getDispalyList(Page utipage,String username, String mobile) {
        PageHelper.startPage(utipage.getPage(),utipage.getLimit());
        username = StringUtils.addPercent(username);
        mobile = StringUtils.addPercent(mobile);
        List<User> users = userMapper.selectByNameAndMobile(username,mobile);
        return PageUtils.page(users);
    }

}

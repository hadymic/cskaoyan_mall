package com.cskaoyan.mall.service.impl.userserverimpl;

import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.userserver.UserManageServer;
import com.cskaoyan.mall.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserManageServerImpl implements UserManageServer {
    @Autowired
    UserMapper userMapper;
    @Override
    public List dispaly(Page utipage) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        PageInfo<Object> pageInfo = new PageInfo<>();
        String id = utipage.getId();
        String username = utipage.getUsername();
        if (username == null){
            username = "%%";
        }
        if (id == null){
            id = "%%";
        }
        return null;
    }
}

package com.cskaoyan.mall.service.impl.userserverimpl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.userserver.UserManageServer;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserManageServerImpl implements UserManageServer {
    private SqlSession sqlSession;
    @Autowired
    UserMapper userMapper;
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Override
    public ListBean dispaly(Page utipage) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String username = utipage.getUsername();
        String id = utipage.getId();
        if (username==null)username="";
        if (id==null)id="%%";
        username = "%"+username+"%";
        List<User> users = mapper.updataByNameAndId(username, id);
        PageInfo<User> pageInfo = new PageInfo<User>(users);
        int total =  (int)pageInfo.getTotal();
        ListBean listBean = new ListBean(users,total);
        return listBean;
    }
}

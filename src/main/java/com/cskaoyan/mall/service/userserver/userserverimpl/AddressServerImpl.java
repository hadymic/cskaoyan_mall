package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.service.userserver.AddressService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressServerImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;
    @Override
        public ListBean getAddressList(Page utipage) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        String userId = utipage.getUserId();
        String name = utipage.getName();
        if (userId=="")userId=null;
        if (name==null)name="";
        name = "%" + name +"%";
        List<User> addresslist = addressMapper.selectByIdAndNameKey(userId, name);
        PageInfo<User> pageInfo = new PageInfo<User>(addresslist);
        long total = pageInfo.getTotal();
        ListBean listBean = new ListBean(addresslist,total);
        return listBean;
    }

}

package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.service.userserver.AddressService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
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
        public ListBean getAddressList(Page utipage,String name,String userId) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        if (userId=="")userId=null;
        if (name==null)name="";
        name = "%" + name +"%";
        List addresslist = addressMapper.selectByIdAndNameKey(userId, name);
        return PageUtils.page(addresslist);
    }

    @Override
    public List getWxAddressList(String username) {
        List<Address> addresses = addressMapper.selectAddress(username);
        return addresses;
    }

}

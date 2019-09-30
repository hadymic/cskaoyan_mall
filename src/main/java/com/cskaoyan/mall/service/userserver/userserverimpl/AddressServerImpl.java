package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.service.userserver.AddressServer;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class AddressServerImpl implements AddressServer {
    @Autowired
    AddressMapper addressMapper;
    @Override
    public ListBean address(Page utipage) {
        return null;
    }
}

package com.cskaoyan.mall.service.wx.address.impl;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.service.wx.address.WxAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class WxAddressServiceImpl implements WxAddressService {
    @Autowired
    RegionMapper regionMapper;
    @Override
    public List selectById(int pid) {
        List list = regionMapper.selectById(pid);
        return list;
    }

}

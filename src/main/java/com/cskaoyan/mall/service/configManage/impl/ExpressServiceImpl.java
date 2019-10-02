package com.cskaoyan.mall.service.configManage.impl;

import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.mapper.SystemMapper;
import com.cskaoyan.mall.service.configManage.ExpressService;
import com.cskaoyan.mall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ExpressServiceImpl implements ExpressService {
    @Autowired
    SystemMapper systemMapper;
    @Override
    public Map<String, Object> experess(Page utipage) {
        int b = systemMapper.updateByKeyNema(utipage.getCskaoyan_mall_express_freight_min(),"cskaoyan_mall_express_freight_min");
        int a = systemMapper.updateByKeyNema(utipage.getCskaoyan_mall_express_freight_value(),"cskaoyan_mall_express_freight_value");
        System system = systemMapper.selectByPrimaryKey(5);
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("cskaoyan_mall_express_freight_min",system.getKeyValue());
        System system1 = systemMapper.selectByPrimaryKey(7);
        map.put("cskaoyan_mall_express_freight_value",system.getKeyValue());
        return map;
    }

    @Override
    public Map experess() {
        System system = systemMapper.selectByPrimaryKey(5);
        Map<String,Object> map = new LinkedHashMap<>();
        map.put("cskaoyan_mall_express_freight_min",system.getKeyValue());
        System system1 = systemMapper.selectByPrimaryKey(7);
        map.put("cskaoyan_mall_express_freight_value",system.getKeyValue());
        return map;
    }
}

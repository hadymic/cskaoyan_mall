package com.cskaoyan.mall.service.statisticalform.impl;

import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.statisticalform.StatService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.vo.StatOrderVo;
import com.cskaoyan.mall.vo.StatUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统计报表Service的实现类
 *
 * @author Zeng-jz
 */
@Service
public class StatServiceImpl implements StatService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    OrderMapper orderMapper;

    /**
     * 查询user的注册信息
     * @return
     */
    @Override
    public Map user() {
        List<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("users");
        List<StatUserVo> statUserVos = userMapper.selectUsersByDay();
        Map<String, Object> map = new HashMap<>();
        map.put("columns", columns);
        map.put("rows", statUserVos);
        return map;
    }

    @Override
    public Map<String, Object> order() {
        List<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("orders");
        columns.add("customers");
        columns.add("amout");
        columns.add("pcr");
        List<StatOrderVo> statOrderVos = orderMapper.selectOrderByDay();
        return null;
    }
}

package com.cskaoyan.mall.service.statisticalform.impl;

import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.statisticalform.StatService;
import com.cskaoyan.mall.vo.statisticalform.StatOrderGoodsVo;
import com.cskaoyan.mall.vo.statisticalform.StatOrderVo;
import com.cskaoyan.mall.vo.statisticalform.StatUserVo;
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
    @Autowired
    OrderGoodsMapper orderGoodsMapper;

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

    /**
     * 查询每日的订单信息
     * @return
     */
    @Override
    public Map<String, Object> order() {
        List<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("orders");
        columns.add("customers");
        columns.add("amount");
        columns.add("pcr");
        List<StatOrderVo> statOrderVos = orderMapper.selectOrderByDay();
        for (StatOrderVo statOrderVo : statOrderVos) {
            statOrderVo.setPcr(statOrderVo.getAmount() / statOrderVo.getCustomers());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("columns", columns);
        map.put("rows", statOrderVos);
        return map;
    }

    /**
     * 查询每日的下单商品信息
     * @return
     */
    @Override
    public Map<String, Object> goods() {
        List<String> columns = new ArrayList<>();
        columns.add("day");
        columns.add("orders");
        columns.add("products");
        columns.add("amount");
        List<StatOrderGoodsVo> statOrderGoodsVos = orderGoodsMapper.selectOrdersByDay();
        Map<String, Object> map = new HashMap<>();
        map.put("columns", columns);
        map.put("rows", statOrderGoodsVos);
        return map;
    }
}

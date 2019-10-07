package com.cskaoyan.mall.service.wx.collect.impl;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.service.wx.collect.CollectService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.WxListBean;
import com.cskaoyan.mall.vo.ordermanagement.UserOrdersVo;
import com.cskaoyan.mall.vo.wx.collect.CollectDetail;
import com.cskaoyan.mall.vo.wx.collect.CollectResultVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jszza
 */
@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    CollectMapper collectMapper;

    @Override
    public Map insertOrDelete(Integer userId, Collect collect) {
        Collect collect1 = collectMapper.queryForUpdate(userId,collect);
        Map<String,String> map = new HashMap<>(1);
        if (collect1 == null) {
            collect.setUserId(userId);
            collect.setAddTime(new Date());
            collect.setUpdateTime(new Date());
            collect.setDeleted(false);
            collectMapper.insert(collect);
            map.put("type","add");
        } else if (collect1.getDeleted()){
            collect.setId(collect1.getId());
            collect.setDeleted(false);
            collect.setUpdateTime(new Date());
            collectMapper.updateByPrimaryKeySelective(collect);
            map.put("type","add");
        } else {
            collect.setId(collect1.getId());
            collect.setDeleted(true);
            collect.setUpdateTime(new Date());
            collectMapper.updateByPrimaryKeySelective(collect);
            map.put("type","delete");
        }
        return map;
    }

    @Override
    public CollectResultVo<CollectDetail> queryCollectList(Page page, int userId, Integer type) {
        PageUtils.startPage(page);
        List<CollectDetail> collectList = collectMapper.queryCollectList(userId, type);
        PageInfo<CollectDetail> pageInfo = new PageInfo<>(collectList);
        return new CollectResultVo<>(pageInfo.getList(), pageInfo.getPages());
    }
}

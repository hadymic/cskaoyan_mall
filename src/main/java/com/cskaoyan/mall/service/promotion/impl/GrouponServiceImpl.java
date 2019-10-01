package com.cskaoyan.mall.service.promotion.impl;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GrouponMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.service.promotion.GrouponService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.GrouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GrouponServiceImpl implements GrouponService {
    @Autowired
    private GrouponRulesMapper grouponRulesMapper;

    @Autowired
    private GrouponMapper grouponMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public ListBean<GrouponRules> queryGrouponRuless(Page page, Integer goodsId) {
        PageUtils.startPage(page);
        List<GrouponRules> list = grouponRulesMapper.queryGrouponRuless(goodsId);
        return PageUtils.page(list);
    }

    @Override
    public String insertGrouponRules(GrouponRules grouponRules) {
        grouponRules.setAddTime(new Date());
        grouponRules.setDeleted(false);
        grouponRulesMapper.insertSelective(grouponRules);
        return null;
    }

    @Override
    public String updateGrouponRules(GrouponRules grouponRules) {
        grouponRules.setUpdateTime(new Date());
        grouponRulesMapper.updateByPrimaryKeySelective(grouponRules);
        return null;
    }

    @Override
    public boolean deleteGrouponRules(Integer id) {
        GrouponRules grouponRules = new GrouponRules();
        grouponRules.setId(id);
        grouponRules.setDeleted(true);
        grouponRules.setUpdateTime(new Date());
        return grouponRulesMapper.updateByPrimaryKeySelective(grouponRules) == 1;
    }

    @Override
    public ListBean<GrouponVo> queryGrouponVo(Page page, Integer goodsId) {
        //查询全部父级团购活动
//        List<Groupon> list = grouponMapper.queryGrouponsByGrouponId(0);
//        for (Groupon groupon : list) {
            //查询对应团购规则
//            grouponRulesMapper.selectByPrimaryKey(groupon.getRulesId());
            //查询商品
//        }
        return null;
    }
}

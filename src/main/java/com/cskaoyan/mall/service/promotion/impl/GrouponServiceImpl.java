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
import com.cskaoyan.mall.vo.SubGrouponsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public GrouponRules insertGrouponRules(GrouponRules grouponRules) {
        grouponRules.setAddTime(new Date());
        grouponRules.setDeleted(false);
        return grouponRulesMapper.insertSelectKey(grouponRules) == 1 ? grouponRules : null;
    }

    @Override
    public GrouponRules updateGrouponRules(GrouponRules grouponRules) {
        grouponRules.setUpdateTime(new Date());
        return grouponRulesMapper.updateByPrimaryKeySelective(grouponRules) == 1 ? grouponRules : null;
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
        PageUtils.startPage(page);
        List<GrouponVo> grouponVos = new ArrayList<>();
        //查询对应商品的团购规则
        List<GrouponRules> grouponRules;
        if (goodsId != null) {
            grouponRules = grouponRulesMapper.queryGrouponRuless(goodsId);
        } else {
            //查询全部团购规则
            grouponRules = grouponRulesMapper.queryGrouponRuless(null);
        }

        //循环查询每个规则对应的多条团购活动
        for (GrouponRules grouponRule : grouponRules) {
            GrouponVo vo = new GrouponVo();
            vo.setRules(grouponRule);
            List<Groupon> groupons = grouponMapper.queryGrouponsByRuleId(grouponRule.getId());

            List<SubGrouponsVo> subGrouponsVos = new ArrayList<>();
            for (Groupon groupon : groupons) {
                //如果相同，说明是发起者，封装至groupon
                if (groupon.getUserId().intValue() == groupon.getCreatorUserId().intValue()) {
                    vo.setGroupon(groupon);
                } else {
                    //如果不同，说明是参与者，封装至sub
                    subGrouponsVos.add(new SubGrouponsVo(groupon.getOrderId(), groupon.getUserId()));
                }
            }
            vo.setSubGroupons(subGrouponsVos);
            vo.setGoods(goodsMapper.selectByPrimaryKey(grouponRule.getGoodsId()));
            grouponVos.add(vo);
        }
        return PageUtils.page(grouponVos);
    }
}

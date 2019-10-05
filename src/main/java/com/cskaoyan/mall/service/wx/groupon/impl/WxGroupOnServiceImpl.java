package com.cskaoyan.mall.service.wx.groupon.impl;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.service.wx.groupon.WxGroupOnService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.WxListBean;
import com.cskaoyan.mall.vo.wx.home.GrouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxGroupOnServiceImpl implements WxGroupOnService {

    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public WxListBean selectByPage(Page page) {
        PageUtils.startPage(page);
        List<GrouponVo> grouponVoList = grouponRulesMapper.selectAll();
        for (GrouponVo grouponVo : grouponVoList) {
            Goods goods = goodsMapper.selectById(grouponVo.getGoods_id());
            grouponVo.setGoods(goods);
        }
        return PageUtils.wxPage(grouponVoList);
    }
}

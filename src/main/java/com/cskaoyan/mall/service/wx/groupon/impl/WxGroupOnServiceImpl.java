package com.cskaoyan.mall.service.wx.groupon.impl;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.wx.groupon.WxGroupOnService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.WxListBean;
import com.cskaoyan.mall.vo.ordermanagement.HandleOption;
import com.cskaoyan.mall.vo.wx.groupon.WxMyGroupVo;
import com.cskaoyan.mall.vo.wx.home.GrouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 团购模块service实现类
 *
 * author Zeng-jz
 */
@Service
public class WxGroupOnServiceImpl implements WxGroupOnService {

    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    OrderGoodsMapper orderGoodsMapper;

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

    /**
     * userId 写死为1
     * @param showType
     * @return
     */
    @Override
    public WxListBean selectMyGroupOn(int showType) {
        int userId = 1;
        boolean isCreator = false;
        if (showType == 0){
            isCreator = true;
            List<WxMyGroupVo> wxMyGroupVos = userMapper.selectWxGroupVoById(userId);
            wxMyGroupVos = completeWxMyGroupVos(wxMyGroupVos, userId, isCreator);
            WxListBean wxListBean = new WxListBean(wxMyGroupVos, wxMyGroupVos.size(), null);
            return wxListBean;
        }else {
            List<WxMyGroupVo> wxMyGroupVos = userMapper.selectWxGroupVoJoinById(userId);
            wxMyGroupVos = completeWxMyGroupVos(wxMyGroupVos, userId, isCreator);
            WxListBean wxListBean = new WxListBean(wxMyGroupVos, wxMyGroupVos.size(), null);
            return wxListBean;
        }
    }

    /**
     * 补全
     * @param wxMyGroupVos
     * @return
     */
    private List<WxMyGroupVo> completeWxMyGroupVos(List<WxMyGroupVo> wxMyGroupVos, int userId, boolean isCreator) {
        for (WxMyGroupVo wxMyGroupVo : wxMyGroupVos) {
            wxMyGroupVo.setGroupon(grouponMapper.selectByUidAndGid(wxMyGroupVo.getId(),userId));
            wxMyGroupVo.setRules(grouponRulesMapper.selectByPrimaryKey(wxMyGroupVo.getRulesId()));
            wxMyGroupVo.setHandleOption(HandleOption.get(wxMyGroupVo.getStatusCode(), false));
            wxMyGroupVo.setOrderStatusText(wxMyGroupVo.getHandleOption().getStatusText());
            wxMyGroupVo.setGoodsList(orderGoodsMapper.queryOrderGoodsByOrderId(wxMyGroupVo.getOrderId()));
            wxMyGroupVo.setIsCreator(isCreator);
            wxMyGroupVo.setJoinerCount(grouponMapper.selectJoinerCountByGid(wxMyGroupVo.getId()));
        }
        return wxMyGroupVos;
    }
}

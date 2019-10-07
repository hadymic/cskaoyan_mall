package com.cskaoyan.mall.service.wx.groupon.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.config.MyFileConfig;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.wx.groupon.WxGroupOnService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.WxListBean;
import com.cskaoyan.mall.vo.ordermanagement.HandleOption;
import com.cskaoyan.mall.vo.ordermanagement.OrderInfo;
import com.cskaoyan.mall.vo.wx.groupon.WxGrouponDetailVo;
import com.cskaoyan.mall.vo.wx.groupon.WxMyGroupVo;
import com.cskaoyan.mall.vo.wx.groupon.WxUserVo;
import com.cskaoyan.mall.vo.wx.home.GrouponVo;
import org.apache.shiro.SecurityUtils;
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
    @Autowired
    OrderMapper orderMapper;

    /**
     * 团购列表
     * @param page
     * @return
     */
    @Override
    public WxListBean selectByPage(Page page) {
        PageUtils.startPage(page);
        List<GrouponVo> grouponVoList = grouponRulesMapper.selectAll();
        for (GrouponVo grouponVo : grouponVoList) {
            Goods goods = goodsMapper.selectById(grouponVo.getGoods_id());
            if (!goods.getPicUrl().startsWith("http")){
                goods.setPicUrl(new MyFileConfig().addPicUrl(goods.getPicUrl()));
            }
            grouponVo.setGoods(goods);
        }
        return PageUtils.wxPage(grouponVoList);
    }

    /**
     * @param showType
     * @return
     */
    @Override
    public WxListBean selectMyGroupOn(int showType) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
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
     * 获得商品的详情
     * @param grouponId
     * @return
     */
    @Override
    public WxGrouponDetailVo selectGrouponById(int grouponId) {
        WxGrouponDetailVo returnDetail = new WxGrouponDetailVo();
        List<WxUserVo> joiners = new ArrayList<>();
        List<OrderGoods> orderGoods = new ArrayList<>();
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        List<Groupon> grouponList = grouponMapper.selectGrouponByGrouponId(grouponId);
        for (Groupon groupon : grouponList) {
            WxUserVo wxUserVo = userMapper.selectWxUserVoById(groupon.getUserId());
            joiners.add(wxUserVo);
            List<OrderGoods> orderGoodsList = orderGoodsMapper.queryOrderGoodsByOrderId(groupon.getOrderId());
            for (OrderGoods goods : orderGoodsList) {
                if (!goods.getPicUrl().startsWith("http")){
                    goods.setPicUrl(new MyFileConfig().addPicUrl(goods.getPicUrl()));
                }
                orderGoods.add(goods);
            }
            if (groupon.getUserId().equals(groupon.getCreatorUserId())){
                returnDetail.setCreator(wxUserVo);
                returnDetail.setLinkGrouponId(groupon.getGrouponId());
                GrouponRules rules = grouponRulesMapper.selectByPrimaryKey(groupon.getRulesId());
                if (!rules.getPicUrl().startsWith("http")){
                    rules.setPicUrl(new MyFileConfig().addPicUrl(rules.getPicUrl()));
                }
                returnDetail.setRules(rules);
            }
            if (groupon.getUserId().equals(userId)){
                returnDetail.setGroupon(groupon);
            }
        }
        returnDetail.setJoiners(joiners);
        returnDetail.setOrderGoods(orderGoods);
        OrderInfo orderInfo = orderMapper.queryOrderInfo(returnDetail.getGroupon().getOrderId());
        orderInfo.setHandleOption(HandleOption.get(orderInfo.getOrderStatus(), false));
        orderInfo.setOrderStatusText(orderInfo.getHandleOption().getStatusText());
        returnDetail.setOrderInfo(orderInfo);
        return returnDetail;
    }

    /**
     * 补全
     * @param wxMyGroupVos
     * @return
     */
    private List<WxMyGroupVo> completeWxMyGroupVos(List<WxMyGroupVo> wxMyGroupVos, int userId, boolean isCreator) {
        for (WxMyGroupVo wxMyGroupVo : wxMyGroupVos) {
            wxMyGroupVo.setGroupon(grouponMapper.selectByUidAndGid(wxMyGroupVo.getId(),userId));
            GrouponRules rules = grouponRulesMapper.selectByPrimaryKey(wxMyGroupVo.getRulesId());
            if (!rules.getPicUrl().startsWith("http")){
                rules.setPicUrl(new MyFileConfig().addPicUrl(rules.getPicUrl()));
            }
            wxMyGroupVo.setRules(rules);
            wxMyGroupVo.setHandleOption(HandleOption.get(wxMyGroupVo.getStatusCode(), false));
            wxMyGroupVo.setOrderStatusText(wxMyGroupVo.getHandleOption().getStatusText());
            List<OrderGoods> goodsList = orderGoodsMapper.queryOrderGoodsByOrderId(wxMyGroupVo.getOrderId());
            for (OrderGoods orderGoods : goodsList) {
                if (!orderGoods.getPicUrl().startsWith("http")){
                    orderGoods.setPicUrl(new MyFileConfig().addPicUrl(orderGoods.getPicUrl()));
                }
            }
            wxMyGroupVo.setGoodsList(goodsList);
            wxMyGroupVo.setIsCreator(isCreator);
            wxMyGroupVo.setJoinerCount(grouponMapper.selectJoinerCountByGid(wxMyGroupVo.getId()));
        }
        return wxMyGroupVos;
    }
}

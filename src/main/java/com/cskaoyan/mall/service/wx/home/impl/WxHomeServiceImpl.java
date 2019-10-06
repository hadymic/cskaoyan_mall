package com.cskaoyan.mall.service.wx.home.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.GrouponRulesMapper;
import com.cskaoyan.mall.mapper.SystemMapper;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.cskaoyan.mall.service.mallmanager.BrandService;
import com.cskaoyan.mall.service.mallmanager.CategoryService;
import com.cskaoyan.mall.service.promotion.AdService;
import com.cskaoyan.mall.service.promotion.CouponService;
import com.cskaoyan.mall.service.promotion.TopicService;
import com.cskaoyan.mall.service.wx.home.WxHomeService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.wx.home.AppletsConfigVo;
import com.cskaoyan.mall.vo.wx.home.FloorGoodsVo;
import com.cskaoyan.mall.vo.wx.home.GrouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信-分类目录service实现类
 *
 * author: Zeng-jz
 */
@Service
public class WxHomeServiceImpl implements WxHomeService {

    @Autowired
    SystemMapper systemMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    AdService adService;
    @Autowired
    BrandService brandService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CouponService couponService;
    @Autowired
    TopicService topicService;

    @Override
    public Map index() {
        AppletsConfigVo appletsConfigVo = systemMapper.selectAppletsConfigs();
        Map<String, Object> returnMap = new HashMap<>();

        List<Ad> ads = adService.queryAds(new Page(1, 100, "add_time", "asc"), null, null).getItems();
        returnMap.put("banner", ads);

        List<Brand> brands = brandService.list(new Page(1, appletsConfigVo.getBrandListSize(), "update_time", "desc"), null, null).getItems();
        returnMap.put("brandList", brands);

        List<Category> channel = categoryService.queryChannel();
        returnMap.put("channel", channel);

        List<Coupon> couponList = couponService.queryCoupons(new Page(1, 3, "update_time", "desc"), null, 0, 0).getItems();
        returnMap.put("couponList", couponList);

        List<FloorGoodsVo> floorGoodsList = categoryService.selectFloorGoodsList(
                appletsConfigVo.getFloorListSize(), appletsConfigVo.getFloorGoodsListSize());
        returnMap.put("floorGoodsList", floorGoodsList);

        List<GrouponVo> grouponVos = grouponRulesMapper.selectTopFiveByTime();
        for (GrouponVo grouponVo : grouponVos) {
            grouponVo.setGoods(goodsMapper.selectById(grouponVo.getGoods_id()));
            grouponVo.setGroupon_price(grouponVo.getGoods().getCounterPrice().intValue() - grouponVo.getGroupon_price());
        }
        returnMap.put("grouponList", grouponVos);

        List<Goods> hotGoodsList = goodsMapper.selectTopByHot(appletsConfigVo.getHotGoodsListSize());
        returnMap.put("hotGoodsList", hotGoodsList);

        List<Goods> newGoodsList = goodsMapper.selectTopByNew(appletsConfigVo.getNewGoodsListSize());
        returnMap.put("newGoodsList", newGoodsList);

        List<Topic> topicList = topicService.queryTopics(new Page(1, appletsConfigVo.getTopicListSize(), "update_time", "desc"),
                null, null).getItems();
        returnMap.put("topicList", topicList);

        return returnMap;
    }
}

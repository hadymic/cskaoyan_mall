package com.cskaoyan.mall.service.wx.home.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.config.MyFileConfig;
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
import com.cskaoyan.mall.util.RecommendUtils;
import com.cskaoyan.mall.vo.wx.home.AppletsConfigVo;
import com.cskaoyan.mall.vo.wx.home.FloorGoodsVo;
import com.cskaoyan.mall.vo.wx.home.GrouponVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信-首页service实现类
 * <p>
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
        for (Ad ad : ads) {
            ad.setUrl(addUrl(ad.getUrl()));
        }
        returnMap.put("banner", ads);

        List<Brand> brands = brandService.list(new Page(1, appletsConfigVo.getBrandListSize(), "update_time", "desc"), null, null).getItems();
        for (Brand brand : brands) {
            brand.setPicUrl(addUrl(brand.getPicUrl()));
        }
        returnMap.put("brandList", brands);

        List<Category> channel = categoryService.queryChannel();
        RecommendUtils.addCategoryList(channel);
        for (Category category : channel) {
            category.setIconUrl(addUrl(category.getIconUrl()));
            category.setPicUrl(addUrl(category.getPicUrl()));
        }
        returnMap.put("channel", channel);

        List<Coupon> couponList = couponService.queryCoupons(new Page(1, 3, "update_time", "desc"), null, 0, 0).getItems();
        returnMap.put("couponList", couponList);

        List<FloorGoodsVo> floorGoodsList = categoryService.selectFloorGoodsList(
                appletsConfigVo.getFloorListSize(), appletsConfigVo.getFloorGoodsListSize());
        returnMap.put("floorGoodsList", floorGoodsList);

        List<GrouponVo> grouponVos = grouponRulesMapper.selectTopFiveByTime();
        for (GrouponVo grouponVo : grouponVos) {
            Goods goods = goodsMapper.selectById(grouponVo.getGoods_id());
            goods.setPicUrl(addUrl(goods.getPicUrl()));
            grouponVo.setGoods(goods);
            grouponVo.setGroupon_price(grouponVo.getGoods().getCounterPrice().intValue() - grouponVo.getGroupon_price());
        }
        returnMap.put("grouponList", grouponVos);

        List<Goods> hotGoodsList = goodsMapper.selectTopByHot(appletsConfigVo.getHotGoodsListSize());
        for (Goods goods : hotGoodsList) {
            goods.setPicUrl(addUrl(goods.getPicUrl()));
        }
        returnMap.put("hotGoodsList", hotGoodsList);

        List<Goods> newGoodsList = goodsMapper.selectTopByNew(appletsConfigVo.getNewGoodsListSize());
        for (Goods goods : newGoodsList) {
            goods.setPicUrl(addUrl(goods.getPicUrl()));
        }
        returnMap.put("newGoodsList", newGoodsList);

        List<Topic> topicList = topicService.queryTopics(new Page(1, appletsConfigVo.getTopicListSize(), "update_time", "desc"),
                null, null).getItems();
        for (Topic topic : topicList) {
            topic.setPicUrl(addUrl(topic.getPicUrl()));
        }
        returnMap.put("topicList", topicList);

        return returnMap;
    }

    private String addUrl(String url) {
        if (url != null && !url.startsWith("http")) {
            return new MyFileConfig().addPicUrl(url);
        }
        return url;
    }
}

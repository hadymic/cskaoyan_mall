package com.cskaoyan.mall.service.wx.goods.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.wx.goods.WxGoodsService;
import com.cskaoyan.mall.vo.wx.goodsmanagement.CommentVo;
import com.cskaoyan.mall.vo.wx.goodsmanagement.WxGoodsDetailVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxGoodsServiceImpl implements WxGoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    BrandMapper brandMapper;

    @Override
    public Map<String, Object> showGoodsByCategory(int id) {
        Map<String, Object> map = new HashMap<>();
        Category parentCategory = categoryMapper.selectByPrimaryKey(id);
        Category currentCategory = null;
        List<Category> brotherCategory = null;
        if (parentCategory.getPid() == 0) {//点击父类进入子类显示第一个子类
            brotherCategory = categoryMapper.selectCategoryListByPid(id);
            currentCategory = brotherCategory.get(0);
        }
        map.put("currentCategory", currentCategory);
        map.put("brotherCategory", brotherCategory);
        map.put("parentCategory", parentCategory);
        return map;
    }

    @Override
    public WxGoodsDetailVo selectWxGoodsDatail(int id) {
        WxGoodsDetailVo wxGoodsDetailVo = new WxGoodsDetailVo();
        Map<String, Object> map = new HashMap<>();
        List<GoodsSpecification> specifications = goodsSpecificationMapper.selectSpecificationsByGoodsId(id);
        for (GoodsSpecification specification : specifications) {
            List<GoodsSpecification> Existingspecifications = (List<GoodsSpecification>) map.get(specification.getSpecification());//商品详情之规格自由组合
            if (Existingspecifications == null) {
                List<GoodsSpecification> son = new ArrayList<>();
                son.add(specification);
                map.put(specification.getSpecification(), son);
            } else {
                Existingspecifications.add(specification);
                map.put(specification.getSpecification(), Existingspecifications);
            }

        }
        wxGoodsDetailVo.setSpecificationList(map);

        List<Groupon> groupons = grouponMapper.queryGrouponsByRuleId(id);//位置，不知道要啥
        wxGoodsDetailVo.setGroupons(groupons);

        List<Issue> issues = issueMapper.selectAllIssues();
        wxGoodsDetailVo.setIssues(issues);

        wxGoodsDetailVo.setUserHasCollect(false);
        wxGoodsDetailVo.setComment(new CommentVo());
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        wxGoodsDetailVo.setAttributes(goodsAttributeMapper.selectAttributesByGoodsId(id));
        wxGoodsDetailVo.setBrand(brandMapper.selectByPrimaryKey(goods.getBrandId()));
        wxGoodsDetailVo.setProductList(goodsProductMapper.selectProductsByGoodsId(id));
        wxGoodsDetailVo.setInfo(goods);
        return wxGoodsDetailVo;
    }
}

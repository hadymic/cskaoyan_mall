package com.cskaoyan.mall.service.wx.goods.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.wx.goods.WxGoodsService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.wx.goodsmanagement.CommentVo;
import com.cskaoyan.mall.vo.wx.goodsmanagement.GoodsByCategory;
import com.cskaoyan.mall.vo.wx.goodsmanagement.SpecificationList;
import com.cskaoyan.mall.vo.wx.goodsmanagement.WxGoodsDetailVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.util.*;

@Service
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

    /**
     * @param id
     * @return
     * @author hjl 根据种类显示商品
     */
    @Override
    public Map<String, Object> showGoodsByCategory(int id) {
        Map<String, Object> map = new HashMap<>();
        Category parentCategory = categoryMapper.selectByPrimaryKey(id);
        parentCategory.setChildren(null);
        Category currentCategory;
        List<Category> brotherCategory;
        if (parentCategory.getPid() == 0) {//如果传的是父类categoryId,点击父类进入子类显示第一个子类
            brotherCategory = categoryMapper.selectCategoryListByPid(id);
            currentCategory = brotherCategory.get(0);
        } else {//如果传的是子类categoryId
            currentCategory = categoryMapper.selectByPrimaryKey(id);//找到子类
            parentCategory = categoryMapper.selectByPrimaryKey(currentCategory.getPid());//找到父类
            brotherCategory = categoryMapper.selectCategoryListByPid(parentCategory.getId());//找到兄弟


        }
        map.put("currentCategory", currentCategory);
        map.put("brotherCategory", brotherCategory);
        map.put("parentCategory", parentCategory);
        return map;
    }

    /**
     * @param id
     * @return
     * @author hjl 显示单个商品详情
     */
    @Override
    public WxGoodsDetailVo selectWxGoodsDatail(int id) {
        WxGoodsDetailVo wxGoodsDetailVo = new WxGoodsDetailVo();
        List<GoodsSpecification> specifications = goodsSpecificationMapper.selectSpecificationsByGoodsId(id);
        Set<String> set = new TreeSet<>();
        for (GoodsSpecification specification : specifications) {
            set.add(specification.getSpecification());
        }
        List<SpecificationList> specificationLists = new ArrayList<>();
        Iterator iterator =set.iterator();
        while (iterator.hasNext()){
            String name = (String) iterator.next();
            List<GoodsSpecification> newSpec = new ArrayList<>();
            for (GoodsSpecification specification : specifications) {
                if (name.equals(specification.getSpecification())){
                    newSpec.add(specification);
                }
            }
            specificationLists.add(new SpecificationList(name,newSpec));
        }
        wxGoodsDetailVo.setSpecificationList(specificationLists);

        List<Groupon> groupons = grouponMapper.queryGrouponsByRuleId(id);//位置，不知道要啥
        wxGoodsDetailVo.setGroupons(groupons);

        List<Issue> issue = issueMapper.selectAllIssues();
        wxGoodsDetailVo.setIssue(issue);

        wxGoodsDetailVo.setUserHasCollect(false);
        wxGoodsDetailVo.setComment(new CommentVo());
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        wxGoodsDetailVo.setAttributes(goodsAttributeMapper.selectAttributesByGoodsId(id));
        wxGoodsDetailVo.setBrand(brandMapper.selectByPrimaryKey(goods.getBrandId()));
        wxGoodsDetailVo.setProductList(goodsProductMapper.selectProductsByGoodsId(id));
        wxGoodsDetailVo.setInfo(goods);
        return wxGoodsDetailVo;
    }

    @Override
    public int count() {
        return goodsMapper.queryGoodsNumber();

    }

    @Override
    public GoodsByCategory PageGoodsByCategory(Page page, int categoryId) {
        List<Goods> goodsList = goodsMapper.selectGoodsListByCategoryId(categoryId);
        int count = goodsList.size();
        List<Category> filterCategoryList = null;
        for (Goods goods : goodsList) {
            Category category = categoryMapper.selectByPrimaryKey(goods.getCategoryId());
            if (filterCategoryList == null) {
                filterCategoryList = new ArrayList<>();
            }
            filterCategoryList.add(category);
        }
        return new GoodsByCategory(goodsList, count, filterCategoryList);
    }

    /**
     * 显示相关商品
     *
     * @param id
     * @return
     */
    @Override
    public List<Goods> showRelatedGoods(int id) {
        Goods goods1 = goodsMapper.selectByPrimaryKey(id);//找到本商品
        List<Goods> goodsList = goodsMapper.selectGoodsListByCategoryId(goods1.getCategoryId());//根据本商品的categoryId找到所有此类商品
        Iterator iterator = goodsList.iterator();
        while (iterator.hasNext()) {//去除本商品，就是相关商品
            Goods goods = (Goods) iterator.next();
            if (goods.getId() == id) {
                iterator.remove();
            }
        }
        return goodsList;
    }

    @Override
    public GoodsByCategory PageGoodsByBrand(Page page, int brandId) {
        PageUtils.startPage(page);
       List<Goods> goodsList = goodsMapper.selectGoodsListByBrandId(brandId);
       int count = goodsList.size();
        List<Category> filterCategoryList = null;
        for (Goods goods : goodsList) {
            Category category = categoryMapper.selectByPrimaryKey(goods.getBrandId());
            if (filterCategoryList == null) {
                filterCategoryList = new ArrayList<>();
            }
            filterCategoryList.add(category);
        }
        return new GoodsByCategory(goodsList, count, filterCategoryList);
    }
}

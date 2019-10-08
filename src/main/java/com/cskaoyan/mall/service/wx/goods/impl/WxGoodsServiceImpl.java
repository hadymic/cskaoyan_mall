package com.cskaoyan.mall.service.wx.goods.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.wx.goods.WxGoodsService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.RecommendUtils;
import com.cskaoyan.mall.vo.wx.goodsmanagement.CommentVo;
import com.cskaoyan.mall.vo.wx.goodsmanagement.GoodsByCategory;
import com.cskaoyan.mall.vo.wx.goodsmanagement.SpecificationList;
import com.cskaoyan.mall.vo.wx.goodsmanagement.WxGoodsDetailVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author stark_h
 * 商品部分
 */
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
    @Autowired
    GrouponRulesMapper grouponRulesMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    FootprintMapper footprintMapper;
    @Autowired
    CollectMapper collectMapper;


    /**
     * @param id
     * @return
     * @author hjl 根据种类显示商品
     */
    @Override
    public Map<String, Object> showGoodsByCategory(int id) {
        Session session = SecurityUtils.getSubject().getSession();
        Map<String, Object> map = new HashMap<>(3);
        Category parentCategory;
        Category currentCategory = null;
        List<Category> brotherCategory;
        if (id != -2) {
            parentCategory = categoryMapper.selectByPrimaryKey(id);
            if (parentCategory.getPid() == 0) {
                session.removeAttribute("isRecommend");
            }
        }
        // 推荐
        if (id == -2 || session.getAttribute("isRecommend") != null) {
            Integer userId = (Integer) session.getAttribute("userId");
            parentCategory = RecommendUtils.getCategory();
            List<Category> subCategory = categoryMapper.selectRecommendCategoryByUserId(userId);
            Set<Category> set = new HashSet<>();
            set.addAll(subCategory);
            brotherCategory = new ArrayList<>();
            brotherCategory.addAll(set);
            if (id == -2) {
                session.setAttribute("isRecommend", 1);
                currentCategory = brotherCategory.get(0);
            } else {
                for (Category category : brotherCategory) {
                    if (category.getId() == id) {
                        currentCategory = category;
                        break;
                    }
                }
            }
        } else {
            parentCategory = categoryMapper.selectByPrimaryKey(id);
            parentCategory.setChildren(null);
            if (parentCategory.getPid() == 0) {//如果传的是父类categoryId,点击父类进入子类显示第一个子类
                brotherCategory = categoryMapper.selectCategoryListByPid(id);
                currentCategory = brotherCategory.get(0);
            } else {//如果传的是子类categoryId
                currentCategory = categoryMapper.selectByPrimaryKey(id);//找到子类
                parentCategory = categoryMapper.selectByPrimaryKey(currentCategory.getPid());//找到父类
                brotherCategory = categoryMapper.selectCategoryListByPid(parentCategory.getId());//找到兄弟
            }
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
        //浏览历史,添加足迹到footprint表
        Date date = new Date();
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        if (userId != null) {
            int count = footprintMapper.selectByIntUserIdAndGoodsId(userId, id);
            if (count < 1) {
                footprintMapper.insertSelective(new Footprint(userId, id, date, date, false));
            }
        }
        WxGoodsDetailVo wxGoodsDetailVo = new WxGoodsDetailVo();
        List<GoodsSpecification> specifications = goodsSpecificationMapper.selectSpecificationsByGoodsId(id);
        Set<String> set = new TreeSet<>();
        for (GoodsSpecification specification : specifications) {//商品规格的name
            set.add(specification.getSpecification());
        }
        List<SpecificationList> specificationLists = new ArrayList<>();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {  //筛选同一规格名下的具体规格
            String name = (String) iterator.next();
            List<GoodsSpecification> newSpec = new ArrayList<>();
            for (GoodsSpecification specification : specifications) {
                if (name.equals(specification.getSpecification())) {
                    newSpec.add(specification);
                }
            }
            specificationLists.add(new SpecificationList(name, newSpec));
        }
        wxGoodsDetailVo.setSpecificationList(specificationLists);

        List<GrouponRules> grouponRules = grouponRulesMapper.queryGrouponRuless(id);//位置，不知道要啥
        wxGoodsDetailVo.setGroupon(grouponRules);

        List<Issue> issue = issueMapper.selectAllIssues();
        wxGoodsDetailVo.setIssue(issue);
        //查找该用户是否收藏商品,type=0商品，type=1专题收藏
        if (collectMapper.selectCollectByUserIdValueId(userId, id, 0) > 0) {
            wxGoodsDetailVo.setUserHasCollect(true);
        } else {
            wxGoodsDetailVo.setUserHasCollect(false);
        }
        //查找商品评论
        List<Comment> commentList = commentMapper.selectCommentByGoodsId(id);
        int size = commentList.size();//总评论数
        if (commentList.size() > 2) {
            commentList = commentList.subList(0, 2);//大于两条评论只显示两条
        }
        wxGoodsDetailVo.setComment(new CommentVo(commentList, size));
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
    public GoodsByCategory PageGoodsByCategory(Page page, Goods goods) {
        //还要处理热卖，新品，关键字搜索..........全部使用一个sql处理
        List<Goods> goodsList = goodsMapper.selectNeedGoods(goods);
        List<Category> filterCategoryList = new ArrayList<>();
        //goodsList = goodsMapper.selectGoodsListByCategoryId(goods.getCategoryId());
        int count = goodsList.size();
        if (goods.getIsHot() == null && goods.getIsNew() == null && goods.getName() == null) {//显示所有分类
            List<Category> categoryList = categoryMapper.selectCategoryListByPid(0);
            for (Category category : categoryList) {
                List<Category> categoryList2 = categoryMapper.selectCategoryListByPid(category.getId());
                for (Category category1 : categoryList2) {
                    filterCategoryList.add(category1);
                }
            }
        } else {//显示查找到商品的分类,去除重复的
            Set<Integer> set = new TreeSet<>();
            for (Goods goods1 : goodsList) {
                set.add(goods1.getCategoryId());
            }
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                Integer categoryId = (Integer) iterator.next();
                Category category = categoryMapper.selectByPrimaryKey(categoryId);
                filterCategoryList.add(category);
            }
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
            Category category = categoryMapper.selectByPrimaryKey(goods.getCategoryId());
            if (filterCategoryList == null) {
                filterCategoryList = new ArrayList<>();
            }
            filterCategoryList.add(category);
        }
        return new GoodsByCategory(goodsList, count, filterCategoryList);
    }
}

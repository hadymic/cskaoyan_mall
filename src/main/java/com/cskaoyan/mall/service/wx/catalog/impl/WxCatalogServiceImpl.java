package com.cskaoyan.mall.service.wx.catalog.impl;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.config.MyFileConfig;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.service.wx.catalog.WxCatalogService;
import com.cskaoyan.mall.util.RecommendUtils;
import com.cskaoyan.mall.vo.wx.catalog.WxCurrentCategoryVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 微信分类类目Service的实现类
 * <p>
 * author: Zeng-jz
 */
@Service
public class WxCatalogServiceImpl implements WxCatalogService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 根据id查询需要的分类类目，当id=-1时返回分类全部
     *
     * @param id
     * @return
     */
    @Override
    public WxCurrentCategoryVo selectCategoryList(int id) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        if (id == -2) {
            Category currentCategory = RecommendUtils.getCategory();
            List<Category> subCategory = categoryMapper.selectRecommendCategoryByUserId(userId);
            Set<Category> set = new HashSet<>();
            set.addAll(subCategory);
            List<Category> currentSubCategory = new ArrayList<>();
            currentSubCategory.addAll(set);
            return new WxCurrentCategoryVo(null, currentCategory, currentSubCategory);
        }
        if (id == -1) {
            List<Category> categoryList = categoryMapper.selectChannel();
            RecommendUtils.addCategoryList(categoryList);
            categoryList = addUrl(categoryList);
            Category currentCategory = categoryList.get(0);
            List<Category> currentSubCategory;
            if (userId == null) {
                currentSubCategory = categoryMapper.selectChildren(currentCategory.getId());
            } else {
                List<Category> subCategory = categoryMapper.selectRecommendCategoryByUserId(userId);
                Set<Category> set = new HashSet<>();
                set.addAll(subCategory);
                List<Category> newList = new ArrayList<>();
                newList.addAll(set);
                currentSubCategory = newList;
            }
            currentSubCategory = addUrl(currentSubCategory);
            return new WxCurrentCategoryVo(categoryList, currentCategory, currentSubCategory);
        }
        Category currentCategory = categoryMapper.selectByPrimaryKey(id);
        List<Category> currentSubCategory = categoryMapper.selectChildren(id);
        return new WxCurrentCategoryVo(null, currentCategory, currentSubCategory);
    }

    /**
     * 添加图片头
     *
     * @param categoryList
     * @return
     */
    private List<Category> addUrl(List<Category> categoryList) {
        for (Category category : categoryList) {
            if (!category.getPicUrl().startsWith("http")) {
                category.setPicUrl(new MyFileConfig().addPicUrl(category.getPicUrl()));
            }
            if (!category.getIconUrl().startsWith("http")) {
                category.setIconUrl(new MyFileConfig().addPicUrl(category.getIconUrl()));
            }
        }
        return categoryList;
    }
}

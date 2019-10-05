package com.cskaoyan.mall.service.wx.catalog.impl;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.service.wx.catalog.WxCatalogService;
import com.cskaoyan.mall.vo.wx.catalog.WxCurrentCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信分类类目Service的实现类
 *
 * author: Zeng-jz
 */
@Service
public class WxCatalogServiceImpl implements WxCatalogService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 根据id查询需要的分类类目，当id=-1时返回分类全部
     * @param id
     * @return
     */
    @Override
    public WxCurrentCategoryVo selectCategoryList(int id) {
        if (id == -1){
            List<Category> categoryList = categoryMapper.selectChannel();
            Category currentCategory = categoryList.get(0);
            List<Category> currentSubCategory = categoryMapper.selectChildren(currentCategory.getId());
            return new WxCurrentCategoryVo(categoryList, currentCategory, currentSubCategory);
        }
        Category currentCategory = categoryMapper.selectByPrimaryKey(id);
        List<Category> currentSubCategory = categoryMapper.selectChildren(id);
        return new WxCurrentCategoryVo(null, currentCategory, currentSubCategory);
    }
}

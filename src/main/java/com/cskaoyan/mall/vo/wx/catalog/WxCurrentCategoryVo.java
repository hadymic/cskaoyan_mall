package com.cskaoyan.mall.vo.wx.catalog;

import com.cskaoyan.mall.bean.Category;

import java.util.List;

public class WxCurrentCategoryVo {

    private List<Category> categoryList;
    private Category currentCategory;
    private List<Category> currentSubCategory;

    public WxCurrentCategoryVo() {
    }

    public WxCurrentCategoryVo(List<Category> categoryList, Category currentCategory, List<Category> currentSubCategory) {
        this.categoryList = categoryList;
        this.currentCategory = currentCategory;
        this.currentSubCategory = currentSubCategory;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public List<Category> getCurrentSubCategory() {
        return currentSubCategory;
    }

    public void setCurrentSubCategory(List<Category> currentSubCategory) {
        this.currentSubCategory = currentSubCategory;
    }
}

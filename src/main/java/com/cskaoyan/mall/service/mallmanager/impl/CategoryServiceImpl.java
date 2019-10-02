package com.cskaoyan.mall.service.mallmanager.impl;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.service.mallmanager.CategoryService;
import com.cskaoyan.mall.vo.CategoryVo;
import com.cskaoyan.mall.vo.goodsMangement.BaseValueLabel;
import com.cskaoyan.mall.vo.goodsMangement.CategoryList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商场Service实现类--商品类目
 *
 * @author Zeng-jz
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 获取所有的商品详细信息
     * @return
     */
    @Override
    public List list() {
        return categoryMapper.selectAll();
    }

    /**
     * 获取L1级别的类目信息
     * @return
     */
    @Override
    public List l1() {
        List<Category> categories = categoryMapper.selectAll();
        List<BaseValueLabel> baseValueLabels = new ArrayList<>();
        for (Category category : categories) {
            baseValueLabels.add(new BaseValueLabel(category.getId(), category.getName()));
        }
        return baseValueLabels;
    }

    /**
     * 修改选定的商品或商品类目
     * @param category
     * @return
     */
    @Override
    public int update(Category category) {
        if (category.getPid() == 0 || category.getPicUrl() == null || category.getIconUrl() == null) {
            return 0;
        }
        return categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 在库中删除选定的商品类目
     * 库中deleted被修改为1，代表删除
     * @param category
     */
    @Override
    public void delete(Category category) {
        for (Category child : category.getChildren()) {
            child.setDeleted(true);
            categoryMapper.updateDeletedById(child);
        }
        categoryMapper.updateDeletedById(category);
    }
}

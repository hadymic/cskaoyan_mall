package com.cskaoyan.mall.service.mallmanager.impl;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.config.MyFileConfig;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.cskaoyan.mall.service.mallmanager.CategoryService;
import com.cskaoyan.mall.vo.BaseValueLabel;
import com.cskaoyan.mall.vo.wx.home.FloorGoodsVo;
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
    @Autowired
    MyFileConfig myFileConfig;
    @Autowired
    KeywordMapper keywordMapper;

    /**
     * 获取所有的商品详细信息
     *
     * @return
     */
    @Override
    public List list() {
        List<Category> categories = categoryMapper.selectAll();
        List<Category> newCategories = new ArrayList<>();
        for (Category category : categories) {
            List<Category> children = category.getChildren();
            for (int i = 0; i < children.size(); i++) {
                Category newCategory = checkUrl(children.get(i));
                if (!newCategory.toString().equals(children.get(i).toString())) {
                    children.set(i, newCategory);
                }
            }
            category.setChildren(children);
            Category newCategory = checkUrl(category);
            newCategories.add(newCategory);
        }
        return newCategories;
    }

    /**
     * 为需要添加url头的数据进行添加
     *
     * @param category
     * @return
     */
    private Category checkUrl(Category category) {
        if (!category.getIconUrl().startsWith("http")) {
            category.setIconUrl(myFileConfig.addPicUrl(category.getIconUrl()));
        }
        if (!category.getPicUrl().startsWith("http")) {
            category.setPicUrl(myFileConfig.addPicUrl(category.getPicUrl()));
        }
        return category;
    }

    /**
     * 获取L1级别的类目信息
     *
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
     *
     * @param category
     * @return
     */
    @Override
    public int update(Category category) {
        if (category.getLevel().equalsIgnoreCase("l2")) {
            if (category.getPid() == 0 || category.getPicUrl() == null || category.getIconUrl() == null) {
                return 0;
            }
        }
        return categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 在库中删除选定的商品类目
     * 库中deleted被修改为1，代表删除
     *
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

    /**
     * 增加商品
     *
     * @param category
     * @return
     */
    @Override
    public Category create(Category category) {
        if (category.getLevel().equalsIgnoreCase("l2") && category.getPid() == 0) {
            return null;
        }
        if (category.getPicUrl() == null || category.getIconUrl() == null ||
                category.getKeywords() == null || category.getDesc() == null) {
            return null;
        }

        category.setIconUrl(myFileConfig.parsePicUrl(category.getIconUrl()));
        category.setPicUrl(myFileConfig.parsePicUrl(category.getPicUrl()));

        //int i1 = keywordMapper.insertKeyWord(category.getKeywords());
        int i = categoryMapper.insertNewCategory(category);
        return checkUrl(category);
    }

    @Override
    public List<Category> queryChannel() {
        return categoryMapper.selectChannel();
    }

    @Override
    public List<FloorGoodsVo> selectFloorGoodsList(int floorListSize, int floorGoodsListSize) {
        List<FloorGoodsVo> floorGoodsList = categoryMapper.selectFloorList(floorListSize);
        for (FloorGoodsVo floorGoods : floorGoodsList) {
            List<Goods> goodsList = categoryMapper.selectFloorGoodsList(floorGoodsListSize, floorGoods.getId());
            for (Goods goods : goodsList) {
                if (!goods.getPicUrl().startsWith("http")) {
                    goods.setPicUrl(myFileConfig.addPicUrl(goods.getPicUrl()));
                }
            }
            floorGoods.setGoodsList(goodsList);
        }
        return floorGoodsList;
    }
}

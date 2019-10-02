package com.cskaoyan.mall.service.goods.impl;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsAttribute;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.bean.GoodsSpecification;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.goodsMangement.BaseValueLabel;
import com.cskaoyan.mall.vo.goodsMangement.CategoryList;
import com.cskaoyan.mall.vo.goodsMangement.GoodsEditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author stark_h
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Override
    public ListBean selectGoods(Page page, Goods goods) {
        PageUtils.startPage(page);
        List<Goods> goodsList = goodsMapper.selectGoodsByGoodsSnOrName(goods);
        return PageUtils.page(goodsList);
    }

    @Override
    public List<BaseValueLabel> selectBrandList() {
        List<BaseValueLabel> brandList = brandMapper.selectBrandList();
        return brandList;
    }

    @Override
    public List<CategoryList> selectCategory() {
        List<BaseValueLabel> list = categoryMapper.selectCategory(0);
        List<CategoryList> categoryLists = new ArrayList<>(list.size());
        for (BaseValueLabel baseValueLabel : list) {
            categoryLists.add(new CategoryList(baseValueLabel.getValue(), baseValueLabel.getLabel(), categoryMapper.selectCategory(baseValueLabel.getValue())));
        }
        return categoryLists;
    }

    @Override
    public void deleteGoods(Goods goods) {
        //删除商品设置goods，product，attribute，specification的deleted=1,update_time
        int goodsId = goods.getId();
        Date date = new Date();
        goods.setUpdateTime(date);
        goods.setDeleted(true);
        goodsMapper.updateByPrimaryKeySelective(goods);
        goodsAttributeMapper.updateAttributeDeleted(goodsId,date);
        goodsSpecificationMapper.updateSpecificationDeleted(goodsId,date);
        goodsProductMapper.updateProductDeleted(goodsId,date);
    }

    @Override
    public GoodsEditVo selectGoodsDetail(int id) {
        Integer categoryId = goodsMapper.selectByPrimaryKey(id).getCategoryId();
        Integer pid = categoryMapper.selectByPrimaryKey(categoryId).getPid();
        int[] categoryIds = {pid, categoryId};
        return new GoodsEditVo(categoryIds, goodsMapper.selectByPrimaryKey(id), goodsAttributeMapper.selectAttributesByGoodsId(id),
                goodsSpecificationMapper.selectSpecificationsByGoodsId(id), goodsProductMapper.selectProductsByGoodsId(id));
    }

    @Override
    public boolean updateGoods(GoodsEditVo goodsEditVo) {
        goodsMapper.updateByPrimaryKeySelective(goodsEditVo.getGoods());//更新商品信息
        int goodsId = goodsEditVo.getGoods().getId();//取到goodsId
        List<GoodsSpecification> specifications = goodsEditVo.getSpecifications();
        for (GoodsSpecification specification : specifications) {//更新规格信息
            if (specification.getId() == null) {
                specification.setGoodsId(goodsId);//设置goodsId
                specification.setDeleted(false);//deleted置为0
                goodsSpecificationMapper.insertSelective(specification);
            } else {//删除规格未返回，如何设置为deleted=1？
                goodsSpecificationMapper.updateByPrimaryKeySelective(specification);//可能删除规格，set deleted=1
            }
        }
        List<GoodsAttribute> attributes = goodsEditVo.getAttributes();//更新商品参数
        for (GoodsAttribute attribute : attributes) {
            if (attribute.getGoodsId() == null) {
                attribute.setGoodsId(goodsId);
                attribute.setDeleted(false);
                goodsAttributeMapper.insertSelective(attribute);
            } else {
                goodsAttributeMapper.updateByPrimaryKeySelective(attribute);
            }
        }
//根据["1.5m床垫*1+枕头*2","浅杏粉"]和goodsId找到对应product
        List<GoodsProduct> products = goodsEditVo.getProducts();
        for (GoodsProduct product : products) {

        }
        return true;
    }
}

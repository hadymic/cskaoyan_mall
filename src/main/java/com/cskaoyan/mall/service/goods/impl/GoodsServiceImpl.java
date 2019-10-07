package com.cskaoyan.mall.service.goods.impl;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.config.MyFileConfig;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.UrlUtils;
import com.cskaoyan.mall.vo.BaseValueLabel;
import com.cskaoyan.mall.vo.goodsmanagement.CategoryList;
import com.cskaoyan.mall.vo.goodsmanagement.GoodsEditVo;
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
    private GoodsMapper goodsMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    private GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    private GoodsProductMapper goodsProductMapper;
    @Autowired
    private MyFileConfig myFileConfig;
    @Autowired
    private  KeywordMapper keywordMapper;

    @Override
    public ListBean selectGoods(Page page, Goods goods) {
        PageUtils.startPage(page);
        List<Goods> goodsList = goodsMapper.selectGoodsByGoodsSnOrName(goods);
        for (Goods goods1 : goodsList) {
            goods1.setPicUrl(myFileConfig.addPicUrl(goods1.getPicUrl()));//添加图片前缀
        }
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
        goodsAttributeMapper.updateAttributeDeleted(goodsId, date);
        goodsSpecificationMapper.updateSpecificationDeleted(goodsId, date);
        goodsProductMapper.updateProductDeleted(goodsId, date);
    }

    @Override//显示图片路径要改
    public GoodsEditVo selectGoodsDetail(int id) {
        Integer categoryId = goodsMapper.selectByPrimaryKey(id).getCategoryId();
        Integer pid = categoryMapper.selectByPrimaryKey(categoryId).getPid();
        int[] categoryIds = {pid, categoryId};
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        goods.setPicUrl(myFileConfig.addPicUrl(goods.getPicUrl()));//添加图片picUrl前缀
        goods.setGallery(UrlUtils.CheckListUrls(goods.getGallery(), true));//添加画廊图片前缀
        List<GoodsSpecification> specifications = goodsSpecificationMapper.selectSpecificationsByGoodsId(id);
        for (GoodsSpecification specification : specifications) {
            specification.setPicUrl(myFileConfig.addPicUrl(specification.getPicUrl()));//添加图片picUrl前缀
        }
        List<GoodsProduct> goodsProducts = goodsProductMapper.selectProductsByGoodsId(id);
        for (GoodsProduct goodsProduct : goodsProducts) {
            goodsProduct.setUrl(myFileConfig.addPicUrl(goodsProduct.getUrl()));//添加图片picUrl前缀
        }
        return new GoodsEditVo(categoryIds, goods, goodsAttributeMapper.selectAttributesByGoodsId(id),
                specifications, goodsProducts);
    }

    /**
     * 商品编辑
     * @param goodsEditVo
     * @return
     */
    //删除的未返回，需要去数据库查找出未删除的数据，设置deleted = 1
    @Override
    public String updateGoods(GoodsEditVo goodsEditVo) {
        Date date = new Date();
        Goods goods = goodsEditVo.getGoods();
        if (goodsMapper.selectGoodsByIdAndGoodsSn(goods) == 1) {
            if (!("个".equals(goods.getUnit()) || "件".equals(goods.getUnit()) || "盒".equals(goods.getUnit()))) {
                return "商品单位更新错误";
            }
            String keywords = goods.getKeywords();
            if (keywords != null){
                String[] strings = keywords.split(",");
                for (String string : strings) {
                   if (keywordMapper.selectUniqueKeyword(string) < 1) {
                       Keyword keyword = new Keyword();
                       keyword.setKeyword(string);
                       keyword.setIsHot(goods.getIsHot());
                       keyword.setUpdateTime(new Date());
                       keywordMapper.insertSelective(keyword);
                   }
                }
            }
            goods.setPicUrl(myFileConfig.parsePicUrl(goods.getPicUrl()));//去除图片picUrl前缀
            //去除gallery图片数组前缀
            String[] gallery = goods.getGallery();
            String[] listUrls = UrlUtils.CheckListUrls(gallery, false);
            goods.setGallery(listUrls);

            goodsMapper.updateByPrimaryKeySelective(goods);//更新商品信息
            int goodsId = goodsEditVo.getGoods().getId();//取到goodsId
            List<GoodsSpecification> specifications = goodsEditVo.getSpecifications();//更新的specifications
            //查找数据库中的specification
            List<GoodsSpecification> goodsSpecifications = goodsSpecificationMapper.selectSpecificationsByGoodsId(goodsId);
            for (GoodsSpecification specification : specifications) {
                if (specification.getId() == null) {//id不存在添加规格
                    specification.setGoodsId(goodsId);//设置goodsId
                    specification.setDeleted(false);//deleted置为0
                    specification.setAddTime(date);//设置添加时间
                    specification.setPicUrl(myFileConfig.parsePicUrl(specification.getPicUrl()));//去除图片picUrl前缀
                    goodsSpecificationMapper.insertSelective(specification);
                }
                for (GoodsSpecification goodsSpecification : goodsSpecifications) {//更新规格信息
                    if (specification.getId() != (goodsSpecification.getId())) {//返回数据中未存在的id代表删除
                        goodsSpecificationMapper.updateSpecificationDeleted(goodsId, date);//复用，goodsId未改变
                    } else {//删除规格设置为deleted=1
                        specification.setUpdateTime(date);//设置更新时间
                        goodsSpecificationMapper.updateByPrimaryKeySelective(specification);//可能删除规格，set deleted=1
                    }
                }
            }
            List<GoodsAttribute> attributes = goodsEditVo.getAttributes();//更新商品参数
            List<GoodsAttribute> goodsAttributes = goodsAttributeMapper.selectAttributesByGoodsId(goodsId); //查找数据库中Attribute
            for (GoodsAttribute attribute : attributes) {
                if (attribute.getGoodsId() == null) {//不存在添加attribute
                    attribute.setGoodsId(goodsId);
                    attribute.setDeleted(false);
                    attribute.setAddTime(date);
                    goodsAttributeMapper.insertSelective(attribute);
                }
                for (GoodsAttribute goodsAttribute : goodsAttributes) {
                    if (goodsAttribute.getId() != attribute.getId()) {//返回数据中未存在的id代表删除
                        goodsAttributeMapper.updateAttributeDeleted(goodsId, date);
                    } else {//已存在，更新attribute
                        attribute.setUpdateTime(date);
                        goodsAttributeMapper.updateByPrimaryKeySelective(attribute);
                    }
                }
            }
            //根据["1.5m床垫*1+枕头*2","浅杏粉"]和goodsId找到对应product更新
            List<GoodsProduct> products = goodsEditVo.getProducts();
            for (GoodsProduct product : products) {
                product.setUpdateTime(date);
                product.setGoodsId(goodsId);
                product.setUrl(myFileConfig.parsePicUrl(product.getUrl()));//去除图片Url前缀
                goodsProductMapper.updateProductBySpecAndGoodsId(product);
            }
            //逻辑判断
               return "success";
        } else if (goodsMapper.selectGoodsByGoodsSn(goods) > 0) {//根据goodsSn查询为1，说明已存在(本商品)
            return "商品编号已存在,更新失败";
        }//商品编号不修改，
        return "success";
    }

    /**
     * 商品上架
     *
     * @param goodsEditVo
     * @return
     */
    @Override
    public String createGoods(GoodsEditVo goodsEditVo) {
        Date date = new Date();
        Goods goods = goodsEditVo.getGoods();
        //判断goods_Sn是否已经存在,不存在返回false，
        if (goodsMapper.selectGoodsByGoodsSn(goods) > 0) {//根据goodsSn查询不为0，说明已存在
            return "商品编号已存在";
        }

        if (!("个".equals(goods.getUnit()) || "件".equals(goods.getUnit()) || "盒".equals(goods.getUnit()))) {
            return "商品单位错误";
        }
        //将keywords 插入到keyword表中
        String keywords = goods.getKeywords();
        if (keywords != null){
            String[] strings = keywords.split(",");
            for (String string : strings) {
                if (keywordMapper.selectUniqueKeyword(string) <1) {
                    Keyword keyword = new Keyword();
                    keyword.setKeyword(string);
                    keyword.setIsHot(goods.getIsHot());
                    int i = goods.getSortOrder();
                    keyword.setSortOrder(i);
                    keyword.setAddTime(new Date());
                    keywordMapper.insertSelective(keyword);
                }
            }
        }
        goods.setAddTime(date);
        goods.setDeleted(false);
        goods.setPicUrl(myFileConfig.parsePicUrl(goods.getPicUrl()));//去除图片picUrl前缀
        goods.setGallery(UrlUtils.CheckListUrls(goods.getGallery(), false));//去除gallery图片前缀
        goodsMapper.insertSelectKey(goods);

        //添加attribute
        int goodsId = goods.getId();//获取刚添加的商品goodsId
        List<GoodsAttribute> attributes = goodsEditVo.getAttributes();
        for (GoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goodsId);
            attribute.setAddTime(date);
            attribute.setDeleted(false);
            goodsAttributeMapper.insertSelective(attribute);
        }
        List<GoodsProduct> products = goodsEditVo.getProducts();
        for (GoodsProduct product : products) {
            product.setAddTime(date);
            product.setGoodsId(goodsId);
            product.setDeleted(false);
            product.setUrl(myFileConfig.parsePicUrl(product.getUrl()));//去除图片Url前缀
            goodsProductMapper.insertSelective(product);
        }
        List<GoodsSpecification> specifications = goodsEditVo.getSpecifications();
        for (GoodsSpecification specification : specifications) {
            specification.setAddTime(date);
            specification.setGoodsId(goodsId);
            specification.setDeleted(false);
            specification.setPicUrl(myFileConfig.parsePicUrl(specification.getPicUrl()));//去除图片picUrl前缀
            goodsSpecificationMapper.insertSelective(specification);
        }
        return "success";
    }
}

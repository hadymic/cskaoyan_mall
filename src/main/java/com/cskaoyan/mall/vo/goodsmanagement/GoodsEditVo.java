package com.cskaoyan.mall.vo.goodsmanagement;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.GoodsAttribute;
import com.cskaoyan.mall.bean.GoodsProduct;
import com.cskaoyan.mall.bean.GoodsSpecification;
import javax.validation.Valid;
import java.util.List;


/**
 * 商品编辑bean
 *
 * @author stark_h
 */
public class GoodsEditVo {
    int[] categoryIds;
    @Valid
    Goods goods;

    List<GoodsAttribute> attributes;

    List<GoodsSpecification> specifications;
    @Valid
    List<GoodsProduct> products;

    public GoodsEditVo() {
    }

    public GoodsEditVo(int[] categoryIds, Goods goods, List<GoodsAttribute> attributes, List<GoodsSpecification> specifications, List<GoodsProduct> products) {
        this.categoryIds = categoryIds;
        this.goods = goods;
        this.attributes = attributes;
        this.specifications = specifications;
        this.products = products;
    }

    public int[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(int[] categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<GoodsAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<GoodsAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<GoodsSpecification> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<GoodsSpecification> specifications) {
        this.specifications = specifications;
    }

    public List<GoodsProduct> getProducts() {
        return products;
    }

    public void setProducts(List<GoodsProduct> products) {
        this.products = products;
    }
}

package com.cskaoyan.mall.service.mallmaneger.impl;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.service.mallmaneger.BrandService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 商场Service实现类--商品制造商
 *
 * @author Zeng-jz
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandMapper brandMapper;

    /**
     * 根据获取的数据对品牌商信息进行查询
     * @param page
     * @param id
     * @param name
     * @return
     */
    @Override
    public ListBean<Brand> list(Page page, String id, String name) {
        PageUtils.startPage(page);
        List<Brand> brands = brandMapper.selectAll(id, "%" + name + "%");
        return PageUtils.page(brands);
    }

    /**
     * 删除库中的选定品牌商
     * 仅将选定品牌商数据库中的deleted值更改为1，代表已删除
     * @param brand
     */
    @Override
    public void delete(Brand brand) {
        brand.setDeleted(true);
        int i = brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 修改选定的品牌商信息
     * @param brand
     * @return
     */
    @Override
    public Brand update(Brand brand) {
        if (brand.getDesc() == null || brand.getName() == null || brand.getPicUrl() == null ||
                brand.getFloorPrice().intValue() <= 0) {
            return null;
        }
        int i = brandMapper.updateByPrimaryKeySelective(brand);
        return brandMapper.selectByPrimaryKey(brand.getId());
    }
}

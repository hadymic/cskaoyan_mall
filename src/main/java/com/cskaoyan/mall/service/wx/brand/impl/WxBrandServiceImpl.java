package com.cskaoyan.mall.service.wx.brand.impl;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.service.wx.brand.WxBrandService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.WxListBean;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stark_h
 */
@Service
public class WxBrandServiceImpl implements WxBrandService {
    @Autowired
    BrandMapper brandMapper;

    @Override
    public Brand queryBrandDetail(int id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }

    @Override
    public Map<String,Object> queryAllBrand(Page page) {
        //分页
        PageUtils.startPage(page);
        List<Brand> brandList = brandMapper.selectAllBrand();
        PageInfo<Brand> pageInfo = new PageInfo<>(brandList);
        Map<String,Object> map = new HashMap<>();
        map.put("totalPages",pageInfo.getPages());
        map.put("brandList",brandList);
        return map;
    }
}

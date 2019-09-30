package com.cskaoyan.mall.service.promotion.impl;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.mapper.AdMapper;
import com.cskaoyan.mall.service.promotion.AdService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 广告管理Service实现类
 *
 * @author hadymic
 */
@Service
public class AdServiceImpl implements AdService {
    @Autowired
    private AdMapper adMapper;

    @Override
    public ListBean<Ad> queryAllAds(Page page) {
        PageUtils.startPage(page);
        List<Ad> ads = adMapper.queryAllAds();
        return PageUtils.page(ads);
    }
}

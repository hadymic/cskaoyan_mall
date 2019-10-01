package com.cskaoyan.mall.service.promotion.impl;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.mapper.AdMapper;
import com.cskaoyan.mall.service.promotion.AdService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public ListBean<Ad> queryAds(Page page, String name, String content) {
        PageUtils.startPage(page);
        //如果广告标题不为空
        if (name != null) {
            name = "%" + name + "%";
        }
        //如果广告内容不为空
        if (content != null) {
            content = "%" + content + "%";
        }
        List<Ad> ads = adMapper.queryAds(name, content);
        return PageUtils.page(ads);
    }

    @Override
    public boolean updateAd(Ad ad) {
        ad.setUpdateTime(new Date());
        return adMapper.updateByPrimaryKeySelective(ad) == 1;
    }

    @Override
    public boolean deleteAd(Integer id) {
        Ad ad = new Ad();
        ad.setId(id);
        ad.setDeleted(true);
        ad.setUpdateTime(new Date());
        return adMapper.updateByPrimaryKeySelective(ad) == 1;
    }

    @Override
    public boolean insertAd(Ad ad) {
        ad.setAddTime(new Date());
        ad.setDeleted(false);
        return adMapper.insert(ad) == 1;
    }
}

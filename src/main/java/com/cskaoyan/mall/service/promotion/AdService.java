package com.cskaoyan.mall.service.promotion;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

import java.util.List;

/**
 * 广告管理Service
 *
 * @author hadymic
 */
public interface AdService {
    ListBean<Ad> queryAllAds(Page page);
}

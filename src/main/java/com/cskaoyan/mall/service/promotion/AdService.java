package com.cskaoyan.mall.service.promotion;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

/**
 * 广告管理Service
 *
 * @author hadymic
 */
public interface AdService {
    /**
     * 查找广告列表
     *
     * @param page
     * @param name
     * @param content
     * @return
     */
    ListBean<Ad> queryAds(Page page, String name, String content);

    /**
     * 修改广告信息
     *
     * @param ad
     * @return
     */
    boolean updateAd(Ad ad);

    /**
     * 删除广告
     *
     * @param id
     * @return
     */
    boolean deleteAd(Integer id);

    /**
     * 添加广告
     *
     * @param ad
     * @return
     */
    boolean insertAd(Ad ad);
}

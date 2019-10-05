package com.cskaoyan.mall.service.wx.catalog;

import com.cskaoyan.mall.vo.wx.catalog.WxCurrentCategoryVo;
import org.springframework.stereotype.Service;

/**
 * 微信分类类目Service
 *
 * author: Zeng-jz
 */
@Service
public interface WxCatalogService {
    WxCurrentCategoryVo selectCategoryList(int i);
}

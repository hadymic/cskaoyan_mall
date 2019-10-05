package com.cskaoyan.mall.service.wx.brand;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.util.Page;
import java.util.Map;


/**
 * @author stark_h
 */
public interface WxBrandService {
    Brand queryBrandDetail(int id);

    Map<String,Object> queryAllBrand(Page page);
}

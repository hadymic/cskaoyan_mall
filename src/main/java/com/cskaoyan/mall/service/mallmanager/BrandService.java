package com.cskaoyan.mall.service.mallmanager;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 商场Service--商品制造商
 *
 * @author Zeng-jz
 */
@Service
public interface BrandService {
    ListBean<Brand> list(Page page, String id, String name);

    void delete(Brand brand);

    Brand update(Brand brand);
}

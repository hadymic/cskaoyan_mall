package com.cskaoyan.mall.controller.wx.brand;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.service.wx.brand.WxBrandService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stark_h
 */
@RestController
@RequestMapping("wx/brand")
public class WxBrandController {
    @Autowired
    WxBrandService wxBrandService;

    @GetMapping("detail")
    public BaseRespVo brandDetail(int id) {
        Brand brand = wxBrandService.queryBrandDetail(id);
        Map<String, Object> map = new HashMap<>();
        map.put("brand", brand);
        return BaseRespVo.success(map);
    }

    @GetMapping("list")
    public BaseRespVo brandList(Page page) {
        Map<String, Object> map = wxBrandService.queryAllBrand(page);
        return BaseRespVo.success(map);
    }
}

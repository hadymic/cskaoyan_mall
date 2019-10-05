package com.cskaoyan.mall.controller.wx.catalog;

import com.cskaoyan.mall.service.mallmanager.CategoryService;
import com.cskaoyan.mall.service.wx.catalog.WxCatalogService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.catalog.WxCurrentCategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信-分类目录
 *
 * author: Zeng-jz
 */
@RestController
@RequestMapping("wx/catalog")
public class WxCatalogController {

    @Autowired
    WxCatalogService wxCatalogService;

    /**
     * 分类目录全部分类数据接口
     * @return
     */
    @RequestMapping("index")
    public BaseRespVo index(){
        WxCurrentCategoryVo wxCurrentCategoryVo = wxCatalogService.selectCategoryList(-1);
        return BaseRespVo.success(wxCurrentCategoryVo);
    }

    /**
     * 分类目录当前分类数据接口
     * @param id
     * @return
     */
    @RequestMapping("current")
    public BaseRespVo current(int id){
        WxCurrentCategoryVo wxCurrentCategoryVo = wxCatalogService.selectCategoryList(id);
        return BaseRespVo.success(wxCurrentCategoryVo);
    }
}

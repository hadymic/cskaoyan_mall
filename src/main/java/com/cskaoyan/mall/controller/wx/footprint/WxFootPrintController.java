package com.cskaoyan.mall.controller.wx.footprint;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.service.wx.footprint.WxFootPrintService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.WxFootPrintListBean;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.footprint.FootPrintVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/footprint")
public class WxFootPrintController {
    @Autowired
    WxFootPrintService wxFootPrintService;

    /**
     * 足迹列表
     * author:zt
     * @param page
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo showList(Page page){
        WxFootPrintListBean<FootPrintVo> footPrints= wxFootPrintService.showList(page);
        return  BaseRespVo.success(footPrints);
    }
}

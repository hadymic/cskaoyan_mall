package com.cskaoyan.mall.controller.wx.groupon;

import com.cskaoyan.mall.service.wx.groupon.WxGroupOnService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.WxListBean;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 团购模块
 *
 * author Zeng-jz
 */
@RestController
@RequestMapping("wx/groupon")
public class WxGroupOnController {

    @Autowired
    WxGroupOnService wxGroupOnService;

    /**
     * 显示团购列表
     * @param page
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo list(Page page){
        WxListBean wxListBean = wxGroupOnService.selectByPage(page);
        return BaseRespVo.success(wxListBean);
    }

    /**
     * 团购API-我的团购
     * @param showType
     * @return
     */
    @RequestMapping("my")
    public BaseRespVo my(int showType){
        WxListBean wxListBean = wxGroupOnService.selectMyGroupOn(showType);
        return BaseRespVo.success(wxListBean);
    }

    @RequestMapping("detail")
    public BaseRespVo detail(int grouponId){
        //wxGroupOnService.selectGrouponById();
        return BaseRespVo.success(null);
    }
}

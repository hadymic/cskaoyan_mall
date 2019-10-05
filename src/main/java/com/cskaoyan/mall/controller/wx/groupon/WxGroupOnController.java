package com.cskaoyan.mall.controller.wx.groupon;

import com.cskaoyan.mall.service.wx.groupon.WxGroupOnService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.WxListBean;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/groupon")
public class WxGroupOnController {

    @Autowired
    WxGroupOnService wxGroupOnService;

    @RequestMapping("list")
    public BaseRespVo list(Page page){
        WxListBean wxListBean = wxGroupOnService.selectByPage(page);
        return BaseRespVo.success(wxListBean);
    }
}

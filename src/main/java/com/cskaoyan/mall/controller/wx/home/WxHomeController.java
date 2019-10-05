package com.cskaoyan.mall.controller.wx.home;

import com.cskaoyan.mall.service.wx.home.WxHomeService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("wx/home")
public class WxHomeController {

    @Autowired
    WxHomeService wxHomeService;

    @RequestMapping("index")
    public BaseRespVo index(){
        Map map = wxHomeService.index();
        return BaseRespVo.success(map);
    }

}

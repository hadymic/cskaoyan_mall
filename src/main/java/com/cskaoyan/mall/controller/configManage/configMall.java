package com.cskaoyan.mall.controller.configManage;

import com.cskaoyan.mall.service.configManage.ExpressService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("admin/config/")
public class configMall {
    @RequestMapping("mall")
    public BaseRespVo mall(Page utipage, Model model){
        return null;
    }
   // @RequestMapping("mall")
   // public BaseRespVo mall(){ }

    @Autowired
    ExpressService expressService;
    @RequestMapping(value = "express",method = RequestMethod.POST)
    public BaseRespVo express(@RequestBody Page utipage) {
        Map experess = expressService.experess(utipage);
//        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
//        objectBaseRespVo.setData(experess);
//        objectBaseRespVo.setErrmsg("成功");
//        objectBaseRespVo.setErrno(0);
        return  BaseRespVo.success(experess);
    }
    @RequestMapping("express")
    public BaseRespVo express(){
        Map experess = expressService.experess();
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
//        objectBaseRespVo.setData(experess);
//        objectBaseRespVo.setErrmsg("成功");
//        objectBaseRespVo.setErrno(0);
        return  BaseRespVo.success(experess);
    }
}

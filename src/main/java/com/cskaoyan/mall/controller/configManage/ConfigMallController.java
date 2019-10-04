package com.cskaoyan.mall.controller.configManage;

import com.cskaoyan.mall.service.configManage.ConfigService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.config.ExpressVo;
import com.cskaoyan.mall.vo.config.MallConfigVo;
import com.cskaoyan.mall.vo.config.OrderVo;
import com.cskaoyan.mall.vo.config.WxVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("admin/config/")
@EnableTransactionManagement
public class ConfigMallController {
    @Autowired
    ConfigService configService;

    @GetMapping("mall")
    public BaseRespVo mallGet(){
        MallConfigVo mall = configService.getMall();
        return BaseRespVo.success(mall);
    }
    @PostMapping("mall")
    public BaseRespVo mallPost(@RequestBody MallConfigVo vo){
        boolean flag = configService.updateMall(vo);
        return flag?BaseRespVo.success(null):BaseRespVo.fail("更新失败");
    }

    @RequestMapping(value = "express",method = RequestMethod.POST)
    public BaseRespVo expressGet(@RequestBody ExpressVo expressVo) {
        boolean fiag = configService.experess(expressVo);
        return  fiag?BaseRespVo.success(null):BaseRespVo.fail("更新失败");
    }
    @RequestMapping("express")
    public BaseRespVo expressPost(){
        Map experess = configService.experess();
        return BaseRespVo.success(experess);
    }
    @RequestMapping("order")
    public BaseRespVo oredr(){
        return BaseRespVo.success(configService.getOredr());
    }
    @RequestMapping(value = "order",method = RequestMethod.POST)
    public BaseRespVo order(@RequestBody OrderVo orderVo){
        boolean fiag = configService.getOredr(orderVo);
        return fiag?BaseRespVo.success(null):BaseRespVo.fail("更新失败");
    }
    @RequestMapping("wx")
    public BaseRespVo wxGet(){
        return BaseRespVo.success(configService.getWx());
    }
    @RequestMapping(value = "wx",method = RequestMethod.POST)
    public BaseRespVo wxPost(@RequestBody WxVo wxVo){
        boolean fiag = configService.updateWx(wxVo);
        return fiag?BaseRespVo.success(null):BaseRespVo.fail("更新失败");
    }
}

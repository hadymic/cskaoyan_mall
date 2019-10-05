package com.cskaoyan.mall.controller.configManage;

import com.cskaoyan.mall.service.configManage.ConfigService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.config.ExpressVo;
import com.cskaoyan.mall.vo.config.MallConfigVo;
import com.cskaoyan.mall.vo.config.OrderVo;
import com.cskaoyan.mall.vo.config.WxVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    /**商城配置Get请求（查询）
     * @return
     */
    @GetMapping("mall")
    @RequiresPermissions("admin:config:mall:list")
    public BaseRespVo mallGet(){
        MallConfigVo mall = configService.getMall();
        return BaseRespVo.success(mall);
    }

    /**商城配置POST请求（更新）
     * @param vo
     * @return
     * flag表示操作是否可以继续执行
     */
    @PostMapping("mall")
    @RequiresPermissions("admin:config:mall:updateConfigs")
    public BaseRespVo mallPost(@RequestBody MallConfigVo vo){
        boolean flag =vo.nonVoid();
        if (flag) flag= configService.updateMall(vo);
        return flag?BaseRespVo.success(null):BaseRespVo.fail("更新失败,请检查是否某项为空");
    }

    /**运费配置的POST请求（更新）
     * @param expressVo
     * @return
     * fiag表示操作是否可以继续执行
     */
    @RequiresPermissions("admin:config:express:updateConfigs")
    @RequestMapping(value = "express",method = RequestMethod.POST)
    public BaseRespVo expressPost(@RequestBody ExpressVo expressVo) {
        //判断传入的参数是否存在空字符串
        boolean fiag = expressVo.nonVoid();
        if(fiag)fiag = configService.experess(expressVo);
        return  fiag?BaseRespVo.success(null):BaseRespVo.fail("更新失败,请检查是否某项为空");
    }

    /**运费配置的GET请求（查询）
     * @return
     */
    @RequestMapping("express")
    @RequiresPermissions("admin:config:express:list")
    public BaseRespVo expressGet(){
        Map experess = configService.experess();
        return BaseRespVo.success(experess);
    }

    /**订单配置的GET请求，即是查询（查询）
     * @return
     */
    @RequestMapping("order")
    @RequiresPermissions("admin:config:order:list")
    public BaseRespVo oredrGet(){
        return BaseRespVo.success(configService.getOredr());
    }
    /**订单配置的POST请求（更新）
     * @param
     * @return
     * fiag表示操作是否可以继续执行
     */
    @RequestMapping(value = "order",method = RequestMethod.POST)
    @RequiresPermissions("admin:config:order:updateConfigs")
    public BaseRespVo orderPost(@RequestBody OrderVo orderVo){
        boolean fiag = orderVo.nonVoid();
        if (fiag)fiag = configService.getOredr(orderVo);
        return fiag?BaseRespVo.success(null):BaseRespVo.fail("更新失败,请检查是否某项为空");
    }

    @RequestMapping("wx")
    @RequiresPermissions("admin:config:wx:list")
    public BaseRespVo wxGet(){
        return BaseRespVo.success(configService.getWx());
    }
    /**小程序配置的POST请求（更新）
     * @param
     * @return
     * fiag表示操作是否可以继续执行
     */
    @RequestMapping(value = "wx",method = RequestMethod.POST)
    @RequiresPermissions("admin:config:wx:updateConfigs")
    public BaseRespVo wxPost(@RequestBody WxVo wxVo){
        boolean fiag = wxVo.nonVoid();
        if (fiag) fiag = configService.updateWx(wxVo);
        return fiag?BaseRespVo.success(null):BaseRespVo.fail("更新失败,请检查是否某项为空");
    }
}

package com.cskaoyan.mall.controller.statisticalform;

import com.cskaoyan.mall.service.statisticalform.StatService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 统计报表
 *
 * @author Zeng-jz
 */
@RestController
@RequestMapping("admin/stat")
public class StatController {

    @Autowired
    StatService statService;

    /**
     * 用户统计
     * @return
     */
    @RequestMapping("user")
    @RequiresPermissions(value = "admin:stat:user")
    public BaseRespVo user(){
        Map<String, Object> returnMap = statService.user();
        return BaseRespVo.success(returnMap);
    }

    /**
     * 订单统计
     * @return
     */
    @RequestMapping("order")
    @RequiresPermissions(value = "admin:stat:order")
    public BaseRespVo order(){
        Map<String, Object> returnMap = statService.order();
        return BaseRespVo.success(returnMap);
    }

    @RequestMapping("goods")
    @RequiresPermissions(value = "admin:stat:goods")
    public BaseRespVo goods(){
        Map<String, Object> returnMap = statService.goods();
        return BaseRespVo.success(returnMap);
    }
}

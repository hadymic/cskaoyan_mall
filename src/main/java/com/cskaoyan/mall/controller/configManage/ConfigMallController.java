package com.cskaoyan.mall.controller.configManage;

import com.cskaoyan.mall.service.admin.LogService;
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
    private ConfigService configService;
    @Autowired
    private LogService logService;

    /**
     * 商城配置Get请求（查询）
     *
     * @return
     */
    @GetMapping("mall")
    @RequiresPermissions("admin:config:mall:list")
    public BaseRespVo mallGet() {
        MallConfigVo mall = configService.getMall();
        return BaseRespVo.success(mall);
    }

    /**
     * 商城配置POST请求（更新）
     *
     * @param vo
     * @return flag表示操作是否可以继续执行
     */
    @PostMapping("mall")
    @RequiresPermissions("admin:config:mall:updateConfigs")
    public BaseRespVo mallPost(@RequestBody MallConfigVo vo) {
        boolean flag = vo.nonVoid();
        if (flag) flag = configService.updateMall(vo);
        if (flag) {
            logService.log(1, "修改商城配置", true);
            return BaseRespVo.success(null);
        } else {
            logService.log(1, "修改商城配置", true);
            return BaseRespVo.fail("更新失败,请检查是否某项为空");
        }
    }

    /**
     * 运费配置的POST请求（更新）
     *
     * @param expressVo
     * @return fiag表示操作是否可以继续执行
     */
    @RequiresPermissions("admin:config:express:updateConfigs")
    @PostMapping("express")
    public BaseRespVo expressPost(@RequestBody ExpressVo expressVo) {
        //判断传入的参数是否存在空字符串
        boolean flag = expressVo.nonVoid();
        if (flag) flag = configService.experess(expressVo);
        if (flag) {
            logService.log(1, "修改运费配置", true);
            return BaseRespVo.success(null);
        } else {
            logService.log(1, "修改运费配置", true);
            return BaseRespVo.fail("更新失败,请检查是否某项为空");
        }
    }

    /**
     * 运费配置的GET请求（查询）
     *
     * @return
     */
    @GetMapping("express")
    @RequiresPermissions("admin:config:express:list")
    public BaseRespVo expressGet() {
        Map experess = configService.experess();
        return BaseRespVo.success(experess);
    }

    /**
     * 订单配置的GET请求，即是查询（查询）
     *
     * @return
     */
    @GetMapping("order")
    @RequiresPermissions("admin:config:order:list")
    public BaseRespVo oredrGet() {
        return BaseRespVo.success(configService.getOredr());
    }

    /**
     * 订单配置的POST请求（更新）
     *
     * @param
     * @return fiag表示操作是否可以继续执行
     */
    @PostMapping("order")
    @RequiresPermissions("admin:config:order:updateConfigs")
    public BaseRespVo orderPost(@RequestBody OrderVo orderVo) {
        boolean flag = orderVo.nonVoid();
        if (flag) flag = configService.getOredr(orderVo);
        if (flag) {
            logService.log(1, "修改订单配置", true);
            return BaseRespVo.success(null);
        } else {
            logService.log(1, "修改订单配置", true);
            return BaseRespVo.fail("更新失败,请检查是否某项为空");
        }
    }

    @GetMapping("wx")
    @RequiresPermissions("admin:config:wx:list")
    public BaseRespVo wxGet() {
        return BaseRespVo.success(configService.getWx());
    }

    /**
     * 小程序配置的POST请求（更新）
     *
     * @param
     * @return fiag表示操作是否可以继续执行
     */
    @PostMapping("wx")
    @RequiresPermissions("admin:config:wx:updateConfigs")
    public BaseRespVo wxPost(@RequestBody WxVo wxVo) {
        boolean flag = wxVo.nonVoid();
        if (flag) flag = configService.updateWx(wxVo);
        if (flag) {
            logService.log(1, "修改商小程序配置", true);
            return BaseRespVo.success(null);
        } else {
            logService.log(1, "修改小程序配置", true);
            return BaseRespVo.fail("更新失败,请检查是否某项为空");
        }
    }
}

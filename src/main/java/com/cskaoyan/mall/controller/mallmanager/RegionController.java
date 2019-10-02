package com.cskaoyan.mall.controller.mallmanager;

import com.cskaoyan.mall.service.mallmanager.RegionService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品管理--行政区域
 *
 * @author Zeng-jz
 */
@RestController
@RequestMapping("admin/region")
public class RegionController {

    @Autowired
    RegionService regionService;

    /**
     * 显示所有区域的信息
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo list(){
        List list = regionService.list();
        return BaseRespVo.success(list);
    }
}

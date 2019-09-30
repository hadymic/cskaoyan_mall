package com.cskaoyan.mall.controller.promotion;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.service.promotion.AdService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 广告管理
 *
 * @author hadymic
 */
@RestController
public class AdController {
    @Autowired
    private AdService adService;

    /**
     * 显示广告列表
     *
     * @return
     */
    @RequestMapping("admin/ad/list")
    public BaseRespVo showAdList(Page page) {
        ListBean<Ad> adListBean = adService.queryAllAds(page);
        return BaseRespVo.success(adListBean);
    }
}

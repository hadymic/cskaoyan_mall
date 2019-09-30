package com.cskaoyan.mall.controller.promotion;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.service.promotion.AdService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 广告管理
 *
 * @author hadymic
 */
@RestController
@RequestMapping("admin/ad")
public class AdController {
    @Autowired
    private AdService adService;

    /**
     * 显示广告列表
     *
     * @param page
     * @return
     */
    @GetMapping("list")
    public BaseRespVo showAdList(Page page, String name, String content) {
        ListBean<Ad> adListBean = adService.queryAds(page, name, content);
        return BaseRespVo.success(adListBean);
    }

    /**
     * 添加广告
     *
     * @param ad
     * @return
     */
    @PostMapping("create")
    public BaseRespVo insertAd(@RequestBody Ad ad) {
        boolean flag = adService.insertAd(ad);
        return flag ? BaseRespVo.success(null) : BaseRespVo.fail("广告更新失败");
    }

    /**
     * 修改广告
     *
     * @param ad
     * @return
     */
    @PostMapping("update")
    public BaseRespVo updateAd(@RequestBody Ad ad) {
        boolean flag = adService.updateAd(ad);
        return flag ? BaseRespVo.success(null) : BaseRespVo.fail("广告更新失败");
    }

    /**
     * 删除广告
     *
     * @param ad
     * @return
     */
    @PostMapping("delete")
    public BaseRespVo deleteAd(@RequestBody Ad ad) {
        boolean flag = adService.deleteAd(ad.getId());
        return flag ? BaseRespVo.success(null) : BaseRespVo.fail("广告删除失败");
    }
}

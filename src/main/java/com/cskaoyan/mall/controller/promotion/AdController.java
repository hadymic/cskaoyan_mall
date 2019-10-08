package com.cskaoyan.mall.controller.promotion;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.service.promotion.AdService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @Autowired
    private LogService logService;

    /**
     * 显示广告列表
     *
     * @param page
     * @return
     */
    @GetMapping("list")
    @RequiresPermissions("admin:ad:list")
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
    @RequiresPermissions("admin:ad:create")
    public BaseRespVo insertAd(@RequestBody Ad ad) {
        Ad newAd = adService.insertAd(ad);
        if (newAd != null) {
            logService.log(1, "添加广告", true);
            return BaseRespVo.success(newAd);
        } else {
            logService.log(1, "添加广告", false);
            return BaseRespVo.fail("广告添加失败");
        }
    }

    /**
     * 修改广告
     *
     * @param ad
     * @return
     */
    @PostMapping("update")
    @RequiresPermissions("admin:ad:update")
    public BaseRespVo updateAd(@RequestBody Ad ad) {
        Ad newAd = adService.updateAd(ad);
        if (newAd != null) {
            logService.log(1, "修改广告", true);
            return BaseRespVo.success(newAd);
        } else {
            logService.log(1, "修改广告", false);
            return BaseRespVo.fail("广告修改失败");
        }
    }

    /**
     * 删除广告
     *
     * @param ad
     * @return
     */
    @PostMapping("delete")
    @RequiresPermissions("admin:ad:delete")
    public BaseRespVo deleteAd(@RequestBody Ad ad) {
        boolean flag = adService.deleteAd(ad.getId());
        if (flag) {
            logService.log(1, "删除广告", true);
            return BaseRespVo.success(null);
        } else {
            logService.log(1, "删除广告", false);
            return BaseRespVo.fail("广告删除失败");
        }
    }
}

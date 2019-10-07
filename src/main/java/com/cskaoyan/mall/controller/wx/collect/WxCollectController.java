package com.cskaoyan.mall.controller.wx.collect;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.service.wx.collect.CollectService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jszza
 */
@RestController
@RequestMapping("wx/collect")
public class WxCollectController {
    @Autowired
    CollectService collectService;

    @RequestMapping("addordelete")
    public BaseRespVo addordelete(@RequestBody Collect collect) {
        int id = 1;
        return BaseRespVo.success(collectService.insertOrDelete(id,collect));
    }

    @RequestMapping("list")
    public BaseRespVo collectList(Page page, Integer type){
        int userId = 1;
        return BaseRespVo.success(collectService.queryCollectList(page,userId,type));
    }
}

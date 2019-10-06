package com.cskaoyan.mall.controller.wx.search;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.service.wx.search.WxSearchService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.search.WxSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author Zeng-jz
 *
 * 微信搜索
 */
@RestController
@RequestMapping("wx/search")
public class WxSearchController {

    @Autowired
    WxSearchService wxSearchService;

    /**
     * 搜索关键字
     * @return
     */
    @RequestMapping("index")
    private BaseRespVo index(){
        WxSearchVo wxSearchVo = wxSearchService.index();
        return BaseRespVo.success(wxSearchVo);
    }

    /**
     * 搜索帮助
     * @param keyword
     * @return
     */
    @RequestMapping("helper")
    private BaseRespVo helper(String keyword){
        List<String> hotKeywordList = wxSearchService.selectHotByKerword(keyword);
        return BaseRespVo.success(hotKeywordList);
    }

    @RequestMapping("clearhistory")
    private BaseRespVo clearHistory(){
        boolean flag = wxSearchService.deleltedHistoryKeyword();
        return BaseRespVo.success(flag);
    }
}

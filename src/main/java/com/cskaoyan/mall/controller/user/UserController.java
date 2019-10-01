package com.cskaoyan.mall.controller.user;

import com.cskaoyan.mall.bean.SearchHistory;
import com.cskaoyan.mall.service.userserver.*;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("admin/")
@RestController
public class UserController {
    @Autowired
    UserManageServer userManageServer;
    @RequestMapping("user/list")
    public BaseRespVo userManage(Page utipage, Model model){
        ListBean dispaly = userManageServer.getDispalyList(utipage);
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setData(dispaly);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }
    @Autowired
    AddressServer addressServer;
    @RequestMapping("address/list")
    public BaseRespVo address(Page utipage, Model model){
        ListBean dispaly = addressServer.getAddressList(utipage);
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setData(dispaly);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }
    @Autowired
    CollectServer collectServer;
    @RequestMapping("collect/list")
    public BaseRespVo collect(Page utipage,Model model){
        ListBean dispaly = collectServer.getCollectList(utipage);
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setData(dispaly);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }
    @Autowired
    FootprintServer footprintServer;
    @RequestMapping("footprint/list")
    public BaseRespVo footprint(Page utipage,Model model){
        ListBean dispaly = footprintServer.getFootprintList(utipage);
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setData(dispaly);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }
    @Autowired
    SearchHistoryServer searchHistoryServer;
    @RequestMapping("history/list")
    public BaseRespVo searchHistory(Page utipage,Model model){
        ListBean dispaly = searchHistoryServer.getSearchHistoryList(utipage);
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setData(dispaly);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }
}

package com.cskaoyan.mall.controller.user;
import com.cskaoyan.mall.service.userserver.*;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("admin/")
@RestController
public class UserController {
    @Autowired
    UserManageService userManageService;
    @RequestMapping("user/list")
    public BaseRespVo userManage(Page utipage, Model model){
        ListBean dispaly = userManageService.getDispalyList(utipage);
      /*  BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setData(dispaly);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);*/
        return BaseRespVo.success(dispaly);
    }
    @Autowired
    AddressService addressService;
    @RequestMapping("address/list")
    public BaseRespVo address(Page utipage, Model model){
        ListBean dispaly = addressService.getAddressList(utipage);
//        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
//        objectBaseRespVo.setData(dispaly);
//        objectBaseRespVo.setErrmsg("成功");
//        objectBaseRespVo.setErrno(0);
        return BaseRespVo.success(dispaly);
    }
    @Autowired
    CollectService collectService;
    @RequestMapping("collect/list")
    public BaseRespVo collect(Page utipage,Model model){
        ListBean dispaly = collectService.getCollectList(utipage);
//        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
//        objectBaseRespVo.setData(dispaly);
//        objectBaseRespVo.setErrmsg("成功");
//        objectBaseRespVo.setErrno(0);
        return BaseRespVo.success(dispaly);
    }
    @Autowired
    FootprintService footprintService;
    @RequestMapping("footprint/list")
    public BaseRespVo footprint(Page utipage,Model model){
        ListBean dispaly = footprintService.getFootprintList(utipage);
//        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
//        objectBaseRespVo.setData(dispaly);
//        objectBaseRespVo.setErrmsg("成功");
//        objectBaseRespVo.setErrno(0);
        return  BaseRespVo.success(dispaly);
    }
    @Autowired
    SearchHistoryService searchHistoryService;
    @RequestMapping("history/list")
    public BaseRespVo searchHistory(Page utipage,Model model){
        ListBean dispaly = searchHistoryService.getSearchHistoryList(utipage);
//        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
//        objectBaseRespVo.setData(dispaly);
//        objectBaseRespVo.setErrmsg("成功");
//        objectBaseRespVo.setErrno(0);
        return  BaseRespVo.success(dispaly);
    }
    @Autowired
    FeedbackService feedbackService;
    @RequestMapping(value = "feedback/list")
    public BaseRespVo feedback(Page utipage,Model model){
        ListBean dispaly = feedbackService.getFeedbackList(utipage);
//        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
//        objectBaseRespVo.setData(dispaly);
//        objectBaseRespVo.setErrmsg("成功");
//        objectBaseRespVo.setErrno(0);
        return  BaseRespVo.success(dispaly);
    }
}

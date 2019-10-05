package com.cskaoyan.mall.controller.user;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.service.userserver.*;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("admin/")
@RestController
public class UserController {
    @Autowired
    UserManageService userManageService;
    @RequestMapping("user/list")
    @RequiresPermissions("admin:user:list")
    public BaseRespVo userManage(Page utipage,String username, String mobile){
        ListBean dispaly = userManageService.getDispalyList(utipage,username,mobile);
        return BaseRespVo.success(dispaly);
    }
    @Autowired
    AddressService addressService;
    @RequiresPermissions("admin:address:list")
    @RequestMapping("address/list")
    public BaseRespVo address(Page utipage,String name,String userId){
        ListBean dispaly = addressService.getAddressList(utipage,name,userId);
        return BaseRespVo.success(dispaly);
    }
    @Autowired
    CollectService collectService;
    @RequestMapping("collect/list")
    @RequiresPermissions("admin:collect:list")
    public BaseRespVo collect(Page utipage,String userId,String valueId){
        ListBean dispaly = collectService.getCollectList(utipage,userId,valueId);
        return BaseRespVo.success(dispaly);
    }
    @Autowired
    FootprintService footprintService;
    @RequestMapping("footprint/list")
    @RequiresPermissions("admin:footprint:list")
    public BaseRespVo footprint(Page utipage,String userId,String goodsId){
        ListBean dispaly = footprintService.getFootprintList(utipage,userId,goodsId);
        return  BaseRespVo.success(dispaly);
    }
    @Autowired
    SearchHistoryService searchHistoryService;
    @RequestMapping("history/list")
    @RequiresPermissions("admin:history:list")
    public BaseRespVo searchHistory(Page utipage,String userId,String goodsId){
        ListBean dispaly = searchHistoryService.getSearchHistoryList(utipage,userId,goodsId);
        return  BaseRespVo.success(dispaly);
    }
    @Autowired
    FeedbackService feedbackService;
    @RequestMapping(value = "feedback/list")
    @RequiresPermissions("admin:feedback:list")
    public BaseRespVo feedback(Page utipage,String id,String username){
        ListBean dispaly = feedbackService.getFeedbackList(utipage,id,username);
        return  BaseRespVo.success(dispaly);
    }
}

package com.cskaoyan.mall.controller.user;

import com.cskaoyan.mall.service.userserver.AddressServer;
import com.cskaoyan.mall.service.userserver.UserManageServer;
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
        ListBean dispaly = userManageServer.dispaly(utipage);
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
        ListBean dispaly = addressServer.address(utipage);
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setData(dispaly);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }
    @RequestMapping("collect/list")
    public BaseRespVo collect(Page utipage,Model model){
        ListBean dispaly = addressServer.address(utipage);
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
        objectBaseRespVo.setData(dispaly);
        objectBaseRespVo.setErrmsg("成功");
        objectBaseRespVo.setErrno(0);
        return objectBaseRespVo;
    }
}

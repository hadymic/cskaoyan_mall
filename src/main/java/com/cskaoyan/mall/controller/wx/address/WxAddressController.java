package com.cskaoyan.mall.controller.wx.address;

import com.cskaoyan.mall.service.userserver.AddressService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("wx/")
public class WxAddressController {
    @Autowired
    AddressService addressService;
    /**principal表示的是当前用户的账号名
     * 这是对关于地址管理controller
     * @return
     */
    @RequestMapping("address/list")
    public BaseRespVo wxAddressDispaly(){
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        List wxAddressList = addressService.getWxAddressList(principal);
        return BaseRespVo.success(wxAddressList);
    }
//    @RequestMapping("region/list")
//    public BaseRespVo wxRegionGian(int pid){
//
//    }
}

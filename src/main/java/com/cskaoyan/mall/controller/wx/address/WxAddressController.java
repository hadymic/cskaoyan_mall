package com.cskaoyan.mall.controller.wx.address;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.service.userserver.AddressService;
import com.cskaoyan.mall.service.wx.address.WxAddressService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        //假设"user"为当前用户登陆的账号且唯一
        //String principal = "user";
        List wxAddressList = addressService.getWxAddressList(principal);
        return BaseRespVo.success(wxAddressList);
    }
    /**
     * 获取行政区域表
     */
    @Autowired
    WxAddressService wxAddressService;
    @RequestMapping("region/list")
    public BaseRespVo wxRegionGian(int pid){
        return BaseRespVo.success(wxAddressService.selectById(pid));
    }
    /**
     * 新增收货地址
     */
    @RequestMapping(value = "address/save",method = RequestMethod.POST)
    public BaseRespVo addProfile(@RequestBody Address address){
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
////        //没办法直接获取用户账号，展示用user代替
        //String principal = "user";
        boolean fale = addressService.addProfile(address,principal);
        return fale?BaseRespVo.success(42):BaseRespVo.fail("添加失败");
    }
    //删除收货地址
    @RequestMapping(value = "address/delete",method = RequestMethod.POST)
    public BaseRespVo deleteAddress(@RequestBody Address address){
        boolean flag = addressService.deleteAddress(address.getId());
        return flag?BaseRespVo.success(42):BaseRespVo.fail("删除失败");
    }
    @RequestMapping("address/detail")
    public BaseRespVo addressDetail(int id){
        Address wxAddress = addressService.getWxAddress(id);
        return BaseRespVo.success(wxAddress);
    }
}

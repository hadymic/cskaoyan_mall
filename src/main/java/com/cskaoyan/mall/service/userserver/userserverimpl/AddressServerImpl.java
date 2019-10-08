package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.userserver.AddressService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.TransformUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Component
public class AddressServerImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;
    @Override
        public ListBean getAddressList(Page utipage,String name,String userId) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        if (userId=="")userId=null;
        if (name==null)name="";
        name = "%" + name +"%";
        List addresslist = addressMapper.selectByIdAndNameKey(userId, name);
        return PageUtils.page(addresslist);
    }
    @Autowired
    RegionMapper regionMapper;
    @Override
    public List getWxAddressList(String username) {
        List<Address> addresses = addressMapper.selectAddress(username);
        return addresses;
    }
    @Autowired
    UserMapper userMapper;
    @Override
    //添加地址/修改地址
    @Transactional
    public boolean addProfile(Address address,String username) {
        Integer userId = userMapper.selectByNameGetId(username);
        address.setUserId(userId);
        int fale = 1;
        //判断是否修改了默认地址
        if (address.getIsDefault()){
            List<Address> addressList = getWxAddressList((String) SecurityUtils.getSubject().getPrincipal());
            for (Address a : addressList) {
                a.setIsDefault(false);
                int i = addressMapper.updateByPrimaryKeySelective(a);
                fale = fale * i;
            }
        }
        //判断前台有无传入Id,有则更新，无则插入
        if (address.getId()== 0){
            //已经提出来变成私有方法了
//            //获得省份编码
//            address.setProvinceId(regionMapper.selectByPrimaryKey(address.getProvinceId()).getCode());
//            //获得城市编码
//            address.setCityId(regionMapper.selectByPrimaryKey(address.getCityId()).getCode());
//            //获得地区编码
//            address.setAreaId(regionMapper.selectByPrimaryKey(address.getAreaId()).getCode());
            address.setAddTime(new Date());
            getAddressCode(address);
            fale = fale * addressMapper.insertSelective(address);
        }else {
           // address.setUpdateTime(new Date());
            getAddressCode(address);
            fale = fale * addressMapper.updateByPrimaryKeySelective(address);
        }
        boolean b = TransformUtils.transformBoolean(fale);
        if (b) return true;
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return false;
    }
    //servicec层关于删除地址(隐藏地址)的代码
    private void getAddressCode(Address address){
        //获得省份编码
        address.setProvinceId(regionMapper.selectByPrimaryKey(address.getProvinceId()).getCode());
        //获得城市编码
        address.setCityId(regionMapper.selectByPrimaryKey(address.getCityId()).getCode());
        //获得地区编码
        address.setAreaId(regionMapper.selectByPrimaryKey(address.getAreaId()).getCode());
        //更新时间
        address.setUpdateTime(new Date());
    }
    @Override
    public boolean deleteAddress(int id) {
        return TransformUtils.transformBoolean(addressMapper.concealAddress(id));
    }
    //更改地址页面获取地址的方法
    @Override
    public Address getWxAddress(int id) {
        Address address = addressMapper.selectAddressById(id);
        //数据库cskaoyan_mall_address`存放的是关于cskaoyan_mall_region中的的code
        //前台需要与返回的是cskaoyan_mall_region表中的id
        address.setAreaId(regionMapper.selectByCode(address.getAreaId()).getId());
        address.setCityId(regionMapper.selectByCode(address.getCityId()).getId());
        address.setProvinceId(regionMapper.selectByCode(address.getProvinceId()).getCode());
        return address;
    }
}

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    //添加地址
    public boolean addProfile(Address address,String username) {
        Integer userId = userMapper.selectByNameGetId(username);
        address.setUserId(userId);
        int fale = 0;
        //查询数据库是否存在当前存入的Id,有则更新，无则插入
        if (address.getId()==null){
            //获得省份编码
            address.setProvinceId(regionMapper.selectByPrimaryKey(address.getProvinceId()).getCode());
            //获得城市编码
            address.setCityId(regionMapper.selectByPrimaryKey(address.getCityId()).getCode());
            //获得地区编码
            address.setAreaId(regionMapper.selectByPrimaryKey(address.getAreaId()).getCode());
            address.setAddTime(new Date());
            fale = addressMapper.insertSelective(address);
        }else {
            address.setUpdateTime(new Date());
            fale = addressMapper.updateByPrimaryKey(address);
        }
        return TransformUtils.transformBoolean(fale);
    }

    //servicec层关于删除地址(隐藏地址)的代码
    @Override
    public boolean deleteAddress(int id) {
        return TransformUtils.transformBoolean(addressMapper.concealAddress(id));
    }
    //更改地址页面获取地址的方法
    @Override
    public Address getWxAddress(int id) {
        return  addressMapper.selectAddressById(id);
    }

    /**TransformUtils.transformBoolean i 是否为0，为0则为false
     * 用户更新地址操作在service层的代码
     * @param address
     * @return
     */

}

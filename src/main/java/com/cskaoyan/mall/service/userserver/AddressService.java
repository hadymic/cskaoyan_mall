package com.cskaoyan.mall.service.userserver;

import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressService {
    ListBean getAddressList(Page utipage,String name ,String userId);

    List getWxAddressList(@Param("username") String username);
}

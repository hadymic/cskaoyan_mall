package com.cskaoyan.mall.service.wx.groupon;

import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.WxListBean;
import org.springframework.stereotype.Service;

@Service
public interface WxGroupOnService {
    WxListBean selectByPage(Page page);
}

package com.cskaoyan.mall.service.wx.footprint;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.WxFootPrintListBean;
import com.cskaoyan.mall.vo.wx.footprint.FootPrintVo;

public interface WxFootPrintService {
    WxFootPrintListBean<FootPrintVo> showList(Page page);
}

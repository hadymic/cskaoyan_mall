package com.cskaoyan.mall.service.wx.footprint.impl;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.service.wx.footprint.WxFootPrintService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.WxFootPrintListBean;
import com.cskaoyan.mall.vo.wx.footprint.FootPrintVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WxFootPrintServiceImpl implements WxFootPrintService {
    @Autowired
    FootprintMapper footprintMapper;
    @Override
    public WxFootPrintListBean<FootPrintVo> showList(Page page) {
        PageUtils.startPage(page);
       List<FootPrintVo> footPrintVos= footprintMapper.queryFootPrint();
       return  PageUtils.wxFootPrintPage(footPrintVos);

    }
}

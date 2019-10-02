package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.service.userserver.FootprintService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FootprintServiceImpl implements FootprintService {
    @Autowired
    FootprintMapper footprintMapper;
    @Override
    public ListBean getFootprintList(Page utipage,String userId,String goodsId) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        if (userId=="")userId=null;
        if (goodsId=="")goodsId=null;
        List<Footprint> footprints = footprintMapper.selectByUserIdAndGoodsId(userId, goodsId);
        return PageUtils.page(footprints);
    }
}

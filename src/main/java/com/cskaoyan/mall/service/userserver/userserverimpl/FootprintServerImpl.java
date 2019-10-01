package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.mapper.FootprintMapper;
import com.cskaoyan.mall.service.userserver.FootprintServer;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FootprintServerImpl implements FootprintServer {
    @Autowired
    FootprintMapper footprintMapper;
    @Override
    public ListBean getFootprintList(Page utipage) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        String userId = utipage.getUserId();
        String goodsId = utipage.getGoodsId();
        if (userId=="")userId=null;
        if (goodsId=="")goodsId=null;
        List<Footprint> footprints = footprintMapper.selectByUserIdAndGoodsId(userId, goodsId);
        PageInfo<Footprint> pageInfo = new PageInfo(footprints);
        long total = pageInfo.getTotal();
        ListBean<Footprint> footprintListBean = new ListBean<>(footprints, total);
        return footprintListBean;
        //return null;
    }
}

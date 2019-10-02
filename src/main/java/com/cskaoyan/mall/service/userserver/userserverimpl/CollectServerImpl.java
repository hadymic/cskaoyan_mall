package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.service.userserver.CollectService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CollectServerImpl implements CollectService {
    @Autowired
    CollectMapper collectMapper;
    public ListBean getCollectList(Page utipage){
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        String userId = utipage.getUserId();
        String valueId = utipage.getValueId();
        if (userId=="")userId=null;
        if (valueId=="")valueId=null;
        List<Comment> comments = collectMapper.selectByTwoId(userId, valueId);
//        PageInfo<Comment> pageInfo = new PageInfo(comments);
//        long total = pageInfo.getTotal();
//        ListBean<Comment> commentListBean = new ListBean<>(comments, total);
//        return commentListBean;
        return PageUtils.page(comments);
        //return listBean;
    }
}

package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.cskaoyan.mall.service.userserver.FeedbackService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public ListBean getFeedbackList(Page utipage) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        String id = utipage.getId();
        String username = utipage.getUsername();
        if (id=="") id = null;
        if (username=="") username = null;
        List<Feedback> feedbackList = feedbackMapper.selectByUsernameAndId(id,username);
        PageInfo<Feedback> pageInfo = new PageInfo<Feedback>(feedbackList);
        long total = pageInfo.getTotal();
        ListBean listBean = new ListBean(feedbackList,total);
        return listBean;
       // return null;
    }
}

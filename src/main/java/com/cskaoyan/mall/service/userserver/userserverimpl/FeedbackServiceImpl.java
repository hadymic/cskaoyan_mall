package com.cskaoyan.mall.service.userserver.userserverimpl;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.config.MyFileConfig;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.cskaoyan.mall.service.userserver.FeedbackService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.UrlUtils;
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
    public ListBean getFeedbackList(Page utipage,String id,String username) {
        PageHelper.startPage(utipage.getPage(), utipage.getLimit());
        if (id=="") id = null;
        if (username=="") username = null;
        List<Feedback> feedbackList = feedbackMapper.selectByUsernameAndId(id,username);
        for (Feedback feedback : feedbackList) {
<<<<<<< HEAD
            String[] utipag = feedback.getPicUrls();
            for (String picUrl : feedback.getPicUrls()) {
                    picUrl = "http://"+ picUrl;
                    picUrl.trim();
            }
            feedback.setPicUrls(utipag);
        }
=======
            feedback.setPicUrls(UrlUtils.CheckListUrls(feedback.getPicUrls(),true));
        }//hjl， 增加图片前缀
>>>>>>> b561ea635039713e641b0a54b96eb702d26e95e7
        return PageUtils.page(feedbackList);
    }
}

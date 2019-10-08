package com.cskaoyan.mall.service.wx.feedback.impl;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.cskaoyan.mall.service.wx.feedback.WxFeedBackService;
import com.cskaoyan.mall.vo.wx.FeedbackVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WxFeedBackServiceImpl  implements WxFeedBackService {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public void submit(FeedbackVo feedbackVo) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        feedbackVo.setUserId(userId);
        feedbackVo.setAddTime(new Date());
        feedbackVo.setUpdateTime(new Date());
        feedbackMapper.submit(feedbackVo);


    }

}

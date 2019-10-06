package com.cskaoyan.mall.service.wx.feedback.impl;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.cskaoyan.mall.service.wx.feedback.WxFeedBackService;
import com.cskaoyan.mall.vo.wx.FeedbackVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxFeedBackServiceImpl  implements WxFeedBackService {
    @Autowired
    FeedbackMapper feedbackMapper;
    @Override
    public void submit(FeedbackVo feedbackVo) {
        int insert = feedbackMapper.submit(feedbackVo);

    }
}

package com.cskaoyan.mall.service.wx.feedback;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.vo.wx.FeedbackVo;

public interface WxFeedBackService {
    void submit(FeedbackVo feedbackVo);


}

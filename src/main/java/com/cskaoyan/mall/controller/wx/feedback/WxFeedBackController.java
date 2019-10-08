package com.cskaoyan.mall.controller.wx.feedback;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.service.wx.feedback.WxFeedBackService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.wx.FeedbackVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WxFeedBackController {
    @Autowired
    WxFeedBackService wxFeedBackService;
    @PostMapping("wx/feedback/submit")
    public BaseRespVo  submit(@RequestBody FeedbackVo feedbackVo){
          wxFeedBackService.submit(feedbackVo);

          return  BaseRespVo.success(null);
    }
}

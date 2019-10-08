package com.cskaoyan.mall.controller.wx.topic;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.service.wx.topic.WxTopicService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx/topic")
public class WxTopicController {
    @Autowired
    WxTopicService wxTopicService;
    @RequestMapping("list")
    public BaseRespVo topicList(Page page){
     Map<String,Object> map = wxTopicService.queryAllTopic(page);
        return BaseRespVo.success(map);
    }

    @RequestMapping("detail")
    public BaseRespVo topicDetail(int id){
        Map<String,Object> map = wxTopicService.queryTopicDetail(id);
        return BaseRespVo.success(map);
    }

    @RequestMapping("related")
    public BaseRespVo topicRelated(int id){
        //example的related每次都是同样四条数据,不合常理(搞个随机四条?)
        List<Topic> topicList = wxTopicService.queryRelatedTopic(id);
        return BaseRespVo.success(topicList);
    }
}

package com.cskaoyan.mall.service.wx.topic;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.util.Page;

import java.util.List;
import java.util.Map;

public interface WxTopicService {
    Map<String, Object> queryAllTopic(Page page);

    Map<String, Object> queryTopicDetail(int id);

    List<Topic> queryRelatedTopic(int id);
}

package com.cskaoyan.mall.service.promotion;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

public interface TopicService {
    ListBean<Topic> queryTopics(Page page, String title, String subtitle);

    String insertTopic(Topic topic);

    String updateTopic(Topic topic);

    boolean deleteTopic(Integer id);
}

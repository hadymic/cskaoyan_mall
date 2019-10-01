package com.cskaoyan.mall.service.promotion.impl;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.cskaoyan.mall.service.promotion.TopicService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicMapper topicMapper;

    @Override
    public ListBean<Topic> queryTopics(Page page, String title, String subtitle) {
        PageUtils.startPage(page);
        if (!StringUtils.isEmpty(title)) {
            title = title.trim();
        }
        if (!StringUtils.isEmpty(subtitle)) {
            subtitle = subtitle.trim();
        }
        List<Topic> list = topicMapper.queryTopics(title, subtitle);
        return PageUtils.page(list);
    }

    @Override
    public String insertTopic(Topic topic) {
        topic.setAddTime(new Date());
        topic.setDeleted(false);
        topicMapper.insertSelective(topic);
        return null;
    }

    @Override
    public String updateTopic(Topic topic) {
        topic.setUpdateTime(new Date());
        topicMapper.updateByPrimaryKeySelective(topic);
        return null;
    }

    @Override
    public boolean deleteTopic(Integer id) {
        Topic topic = new Topic();
        topic.setId(id);
        topic.setDeleted(true);
        topic.setUpdateTime(new Date());
        return topicMapper.updateByPrimaryKeySelective(topic) == 1;
    }
}

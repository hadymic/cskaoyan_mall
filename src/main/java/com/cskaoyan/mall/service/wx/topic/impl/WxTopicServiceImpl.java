package com.cskaoyan.mall.service.wx.topic.impl;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.cskaoyan.mall.service.wx.topic.WxTopicService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author stark_h
 */
@Service
public class WxTopicServiceImpl implements WxTopicService {
    @Autowired
    TopicMapper topicMapper;
    @Override
    public Map<String, Object> queryAllTopic(Page page) {
        PageUtils.startPage(page);
        List<Topic> topicList = topicMapper.selectAllTopic();
        PageInfo<Topic> pageInfo = new PageInfo<>(topicList);
        Map<String,Object> map = new HashMap<>();
        map.put("data",topicList);
        map.put("count",pageInfo.getTotal());
        return map;
    }

    @Override
    public Map<String, Object> queryTopicDetail(int id) {
        Topic topic = topicMapper.selectByPrimaryKey(id);
        Map<String,Object> map =  new HashMap<>();
        map.put("topic",topic);
        map.put("goods",topic.getGoods());
        return map;
    }
}

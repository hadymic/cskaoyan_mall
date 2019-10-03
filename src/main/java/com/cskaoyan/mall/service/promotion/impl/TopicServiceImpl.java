package com.cskaoyan.mall.service.promotion.impl;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.config.MyFileConfig;
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
    private MyFileConfig myFileConfig;

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
        for (Topic topic : list) {
            topic.setPicUrl(myFileConfig.addPicUrl(topic.getPicUrl()));
        }
        return PageUtils.page(list);
    }

    @Override
    public Topic insertTopic(Topic topic) {
        String readCount = verifyReadCount(topic.getReadCount());
        if (readCount == null) {
            return null;
        }
        String url = myFileConfig.parsePicUrl(topic.getPicUrl());
        topic.setPicUrl(url);
        topic.setReadCount(readCount);
        topic.setAddTime(new Date());
        topic.setDeleted(false);
        return topicMapper.insertSelectKey(topic) == 1 ? topic : null;
    }

    /**
     * 校验阅读量并进行格式化
     *
     * @param readCount
     * @return
     */
    private String verifyReadCount(String readCount) {
        if (StringUtils.isEmpty(readCount)) {
            return "0";
        }
        //如果是纯数字
        if (readCount.matches("^[1-9][0-9]*$")) {
            int n = Integer.parseInt(readCount);
            //如果大于1000则转换为1.0k的形式
            if ((n - 1000) >= 0) {
                int thousand = n / 1000;
                int hundred = (n - thousand * 1000) / 100;
                return "" + thousand + "." + hundred + "k";
            }
            return readCount;
            //如果是m.nk的形式，则转换为m.1k
        } else if (readCount.matches("^[1-9][0-9]*([.][0-9]+)?[k]$")) {
            int index = readCount.indexOf(".");
            if (index == -1 || index == readCount.length() - 1) {
                return readCount;
            }
            return readCount.substring(0, index + 2) + "k";
        } else {
            return null;
        }
    }

    @Override
    public Topic updateTopic(Topic topic) {
        String readCount = verifyReadCount(topic.getReadCount());
        if (readCount == null) {
            return null;
        }
        String url = myFileConfig.parsePicUrl(topic.getPicUrl());
        topic.setPicUrl(url);
        topic.setReadCount(readCount);
        topic.setUpdateTime(new Date());
        return topicMapper.updateByPrimaryKeySelective(topic) == 1 ? topic : null;
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

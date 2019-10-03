package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Topic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TopicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Topic record);

    int insertSelective(Topic record);

    Topic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Topic record);

    int updateByPrimaryKeyWithBLOBs(Topic record);

    int updateByPrimaryKey(Topic record);

    List<Topic> queryTopics(@Param("title") String title, @Param("subtitle") String subtitle);

    int insertSelectKey(Topic record);
}
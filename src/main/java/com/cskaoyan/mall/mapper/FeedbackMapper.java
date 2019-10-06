package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.vo.wx.FeedbackVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FeedbackMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Feedback record);

    int insertSelective(Feedback record);

    Feedback selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Feedback record);

    int updateByPrimaryKey(Feedback record);

    List selectByUsernameAndId(@Param("id") String id, @Param("username") String username);

    int submit(FeedbackVo feedbackVo);
}

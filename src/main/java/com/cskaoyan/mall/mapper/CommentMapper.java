package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Comment;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    /**
     * 显示商品评论
     * @return
     */
    List<Comment> selectComentList();
}

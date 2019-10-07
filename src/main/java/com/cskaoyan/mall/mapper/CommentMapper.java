package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Comment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectCommentList(@Param("comment") Comment comment);

    //设置deleted=1
    void updateCommentDeleted(@Param("id") Integer id);

    /**
     * @author hjl,根据goodsId查找商品的评论
     * @param id
     * @return
     */
    List<Comment> selectCommentByGoodsId(@Param("goodsId") int id);
}

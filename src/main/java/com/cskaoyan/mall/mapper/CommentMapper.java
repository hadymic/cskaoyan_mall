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
     * @author hjl,根据goodsId和type查找商品的评论
     * @param id
     * @return
     */
    List<Comment> selectCommentByGoodsIdAndType(@Param("comment")Comment comment);

    /**
     * 单个商品根据goodsId显示评价
     * @param id
     * @return
     */
    List<Comment> selectCommentByGoodsId(@Param("valueId") int id);
}

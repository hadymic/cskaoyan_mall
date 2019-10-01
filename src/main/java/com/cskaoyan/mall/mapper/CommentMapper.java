package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.List;

public interface CommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

<<<<<<< .merge_file_a07956

=======
    /**
     * 显示商品评论
     * @return
     */
    List<Comment> selectComentList();
>>>>>>> .merge_file_a04840
}

package com.cskaoyan.mall.service.goods.impl;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public ListBean queryComment(Page page,Comment comment) {
        PageUtils.startPage(page);
        List<Comment> commentList = commentMapper.selectCommentList(comment);
        return PageUtils.page(commentList);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentMapper.updateCommentDeleted(comment.getId());
    }

}

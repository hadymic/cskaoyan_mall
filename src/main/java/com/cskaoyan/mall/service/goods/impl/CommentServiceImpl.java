package com.cskaoyan.mall.service.goods.impl;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.config.MyFileConfig;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.ordermanagement.ReplyVo;
import com.cskaoyan.mall.util.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author stark_h
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    MyFileConfig myFileConfig;

    @Override
    public ListBean queryComment(Page page, Comment comment) {
        PageUtils.startPage(page);
        List<Comment> commentList = commentMapper.selectCommentList(comment);
        for (Comment comment1 : commentList) {//数组图片显示
            String[] urlLists = comment1.getPicUrls();
            String[] listUrls = UrlUtils.CheckListUrls(urlLists, false);
            comment1.setPicUrls(listUrls);
                }
        return PageUtils.page(commentList);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentMapper.updateCommentDeleted(comment.getId());
    }

    @Override
    public int updateCommentReply(ReplyVo replyVo) {
        /*Comment comment = new Comment();
        comment.setUpdateTime(new Date());

        commentMapper.updateByPrimaryKeySelective();*/
        return 0;
    }

}

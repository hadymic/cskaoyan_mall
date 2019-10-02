package com.cskaoyan.mall.service.goods.impl;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.config.MyFileConfig;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
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
            String[] newUrls = null;
            for (String picUrl : urlLists) {
                if (!picUrl.startsWith("http")) {
                    String addPicUrl = myFileConfig.addPicUrl(picUrl);
                    newUrls = new String[urlLists.length];
                    for (int i = 0; i < urlLists.length; i++) {
                        newUrls[i] = addPicUrl;
                    }
                    comment1.setPicUrls(newUrls);
                }
            }
        }
        return PageUtils.page(commentList);
    }

    @Override
    public void deleteComment(Comment comment) {
        commentMapper.updateCommentDeleted(comment.getId());
    }

}

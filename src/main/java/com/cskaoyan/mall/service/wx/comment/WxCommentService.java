package com.cskaoyan.mall.service.wx.comment;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.util.Page;

import java.util.Map;

public interface WxCommentService {
    Map<String, Integer> countComment(Comment comment);

    Map<String, Object> showCommentListByShowType(Page page, Comment comment, Integer showType);

    Comment insertComment(Comment comment);//专题评价
}

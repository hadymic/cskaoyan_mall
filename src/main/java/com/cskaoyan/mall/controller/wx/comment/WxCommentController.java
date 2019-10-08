package com.cskaoyan.mall.controller.wx.comment;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.service.wx.comment.WxCommentService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("wx/comment")
public class WxCommentController {
    @Autowired
    WxCommentService wxCommentService;

    @RequestMapping("count")
    public BaseRespVo countComment(Comment comment) {
        Map<String, Integer> map = wxCommentService.countComment(comment);//全部评论，有图评论
        return BaseRespVo.success(map);
    }

    @RequestMapping("list")
    public BaseRespVo commentList(Page page, Comment comment, Integer showType) {
        Map<String, Object> map = wxCommentService.showCommentListByShowType(page, comment, showType);
        return BaseRespVo.success(map);
    }

    @PostMapping("post")
    public BaseRespVo postComment(@RequestBody Comment comment) {
        Comment comment1 = wxCommentService.insertComment(comment);//专题评价
        return BaseRespVo.success(comment1);
    }
}

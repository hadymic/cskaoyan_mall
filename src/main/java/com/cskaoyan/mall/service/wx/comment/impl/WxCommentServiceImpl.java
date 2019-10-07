package com.cskaoyan.mall.service.wx.comment.impl;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.wx.comment.WxCommentService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.wx.comment.WxCommentVo;
import com.cskaoyan.mall.vo.wx.groupon.WxUserVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品评论
 *
 * @author stark_h
 */
@Service
public class WxCommentServiceImpl implements WxCommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, Integer> countComment(Comment comment) {
        List<Comment> commentList = commentMapper.selectCommentByGoodsId(comment.getValueId());//全部评论，有图评论
        int count = 0;
        for (Comment comment1 : commentList) {
            if (comment1.getHasPicture()) {
                count++;
            }
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("allCount", commentList.size());//所有评论总数
        map.put("hasPicCount", count);//有图片评论总数
        return map;
    }

    @Override
    public Map<String, Object> showCommentListByShowType(Page page, Comment comment, Integer showType) {
        PageUtils.startPage(page);
        Map<String, Object> map = new HashMap<>();
        List<WxCommentVo> wxCommentVoList = new ArrayList<>();
        List<Comment> commentList = commentMapper.selectCommentByGoodsId(comment.getValueId());
        for (Comment comment1 : commentList) {
            if (showType == 0) {
                Integer userId = comment1.getUserId();
                WxUserVo wxUserVo = userMapper.selectWxUserVoById(userId);
                wxCommentVoList.add(new WxCommentVo(wxUserVo, comment1.getAddTime(), comment1.getPicUrls(), comment1.getContent()));
            } else {//showType=1
                if (comment1.getHasPicture()) {
                    Integer userId = comment1.getUserId();
                    WxUserVo wxUserVo = userMapper.selectWxUserVoById(userId);
                    wxCommentVoList.add(new WxCommentVo(wxUserVo, comment1.getAddTime(), comment1.getPicUrls(), comment1.getContent()));
                }
            }
        }
        PageInfo<WxCommentVo> pageInfo = new PageInfo<>(wxCommentVoList);
        map.put("data", wxCommentVoList);
        map.put("count", pageInfo.getTotal());//此处总数显示有点问题。。
        map.put("currentPage",page.getPage());
        return map;
    }
}

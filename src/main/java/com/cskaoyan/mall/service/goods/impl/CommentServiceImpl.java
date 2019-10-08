package com.cskaoyan.mall.service.goods.impl;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.config.MyFileConfig;
import com.cskaoyan.mall.mapper.CommentMapper;
import com.cskaoyan.mall.mapper.OrderGoodsMapper;
import com.cskaoyan.mall.mapper.OrderMapper;
import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import com.cskaoyan.mall.vo.ordermanagement.ReplyVo;
import com.cskaoyan.mall.util.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    OrderMapper orderMapper;

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
        /*CommentVo comment = new CommentVo();
        comment.setUpdateTime(new Date());

        commentMapper.updateByPrimaryKeySelective();*/
        return 0;
    }

    @Override
    public int insertComment(Comment comment, int userId) {
        OrderGoods orderGoods = orderGoodsMapper.selectByPrimaryKey(comment.getOrderGoodsId());
        comment.setValueId(orderGoods.getGoodsId());
        comment.setType((byte) 3);
        comment.setUserId(userId);
        comment.setAddTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setDeleted(false);
        commentMapper.insertSelective(comment);

        Order order = orderMapper.queryOrderComments(orderGoods.getOrderId());
        order.setComments((short)(order.getComments() - orderGoods.getNumber()));
        orderMapper.updateByPrimaryKeySelective(order);
        orderGoods.setComment(comment.getId());
        orderGoods.setUpdateTime(new Date());
        return orderGoodsMapper.updateByPrimaryKeySelective(orderGoods);
    }
}

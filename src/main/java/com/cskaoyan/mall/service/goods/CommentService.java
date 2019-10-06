package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.ordermanagement.ReplyVo;

public interface CommentService {

    ListBean queryComment(Page page, Comment comment);

    //设置comment的deletd=1
    void deleteComment(Comment comment);

    /**
     * 订单回复
     * @param replyVo
     * @return
     */
    int updateCommentReply(ReplyVo replyVo);

    /**
     * 添加评论
     * @param comment
     * @param userId
     * @return
     */
    int insertComment(Comment comment, int userId);
}

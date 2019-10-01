package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

public interface CommentService {

    ListBean queryComment(Page page, Comment comment);

    //设置comment的deletd=1
    void deleteComment(Comment comment);
}

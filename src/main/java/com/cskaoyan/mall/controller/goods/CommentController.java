package com.cskaoyan.mall.controller.goods;

import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品管理下商品评论
 * @author stark_h
 */
@RestController
@RequestMapping("admin/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @RequestMapping("list")
    @RequiresPermissions(value = "admin:comment:list")
    public BaseRespVo list(Page page, Comment comment){
        ListBean listBean = commentService.queryComment(page,comment);
        return BaseRespVo.success(listBean);
    }

    @RequestMapping("delete")
    @RequiresPermissions(value = "admin:comment:delete")
    public BaseRespVo deleteComment(@RequestBody Comment comment){
        commentService.deleteComment(comment);
        return BaseRespVo.success(null);
    }
}

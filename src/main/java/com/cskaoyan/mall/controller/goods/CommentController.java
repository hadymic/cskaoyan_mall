package com.cskaoyan.mall.controller.goods;

import com.cskaoyan.mall.service.goods.CommentService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @RequestMapping("list")
    public BaseRespVo list(Page page){
        ListBean listBean = commentService.queryAllComment(page);
        return BaseRespVo.success(listBean);
    }
}

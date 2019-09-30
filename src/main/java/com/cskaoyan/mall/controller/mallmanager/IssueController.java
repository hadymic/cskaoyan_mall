package com.cskaoyan.mall.controller.mallmanager;

import com.cskaoyan.mall.service.mallmanager.IssueService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jszza
 */
@RestController
@RequestMapping("admin/issue")
public class IssueController {
    @Autowired
    IssueService issueService;

    /**
     * 分页查询通用问题
     * @param page
     * @return
     */
    @RequestMapping("list")
    public BaseRespVo issueList(Page page){
        return BaseRespVo.success(issueService.queryIssueList(page));
    }
}

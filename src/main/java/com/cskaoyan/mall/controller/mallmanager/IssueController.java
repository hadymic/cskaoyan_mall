package com.cskaoyan.mall.controller.mallmanager;

import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.service.mallmanager.IssueService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public BaseRespVo issueList(Page page, String question){
        return BaseRespVo.success(issueService.queryIssueList(page,question));
    }

    /**
     * 创建issue
     * @param issue
     * @return
     */
    @RequestMapping("create")
    public BaseRespVo createIssue(@RequestBody Issue issue){
        return BaseRespVo.success(issueService.insertIssue(issue));
    }

    /**
     * 更新issue
     * @param issue
     * @return
     */
    @RequestMapping("update")
    public BaseRespVo updateIssue(@RequestBody Issue issue){
        return BaseRespVo.success(issueService.updateIssue(issue));
    }

    /**
     * 删除issue
     * @param issue
     * @return
     */
    @RequestMapping("delete")
    public BaseRespVo deleteIssue(@RequestBody Issue issue){
        issueService.deleteIssue(issue.getId());
        BaseRespVo baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}

package com.cskaoyan.mall.controller.mallmanager;

import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.service.mallmanager.IssueService;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    private IssueService issueService;
    @Autowired
    private LogService logService;

    /**
     * 分页查询通用问题
     * @param page
     * @return
     */
    @RequestMapping("list")
    @RequiresPermissions(value = "admin:issue:list")
    public BaseRespVo issueList(Page page, String question){
        return BaseRespVo.success(issueService.queryIssueList(page,question));
    }

    /**
     * 创建issue
     * @param issue
     * @return
     */
    @RequestMapping("create")
    @RequiresPermissions(value = "admin:issue:create")
    public BaseRespVo createIssue(@RequestBody Issue issue){
        logService.log(1, "添加通用问题", true);
        return BaseRespVo.success(issueService.insertIssue(issue));
    }

    /**
     * 更新issue
     * @param issue
     * @return
     */
    @RequestMapping("update")
    @RequiresPermissions(value = "admin:issue:update")
    public BaseRespVo updateIssue(@RequestBody Issue issue){
        logService.log(1, "修改通用问题", true);
        return BaseRespVo.success(issueService.updateIssue(issue));
    }

    /**
     * 删除issue
     * @param issue
     * @return
     */
    @RequestMapping("delete")
    @RequiresPermissions(value = "admin:issue:delete")
    public BaseRespVo deleteIssue(@RequestBody Issue issue){
        issueService.deleteIssue(issue.getId());
        logService.log(1, "删除通用问题", true);
        BaseRespVo baseRespVo = new BaseRespVo<>();
        baseRespVo.setErrmsg("成功");
        return baseRespVo;
    }
}

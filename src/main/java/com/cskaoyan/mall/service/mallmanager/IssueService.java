package com.cskaoyan.mall.service.mallmanager;

import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

/**
 * @author jszza
 */
public interface IssueService {

    /**
     * 分页查询通用问题
     * @param page
     * @param question
     * @return
     */
    ListBean<Issue> queryIssueList(Page page,String question);

    /**
     * 创建issue
     * @param issue
     * @return
     */
    Issue insertIssue(Issue issue);

    /**
     * 更新issue
     * @param issue
     * @return
     */
    Issue updateIssue(Issue issue);

    /**
     * 删除issue
     * @param id
     */
    void deleteIssue(Integer id);
}

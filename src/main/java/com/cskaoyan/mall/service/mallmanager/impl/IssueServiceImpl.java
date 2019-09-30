package com.cskaoyan.mall.service.mallmanager.impl;

import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.mapper.IssueMapper;
import com.cskaoyan.mall.service.mallmanager.IssueService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author jszza
 */
@Service
public class IssueServiceImpl implements IssueService {
    @Autowired
    IssueMapper issueMapper;
    @Override
    public ListBean<Issue> queryIssueList(Page page,String question) {
        PageUtils.startPage(page);
        List<Issue> issueList= issueMapper.queryIssueList("%"+question+"%");
        return PageUtils.page(issueList);
    }

    @Override
    public Issue insertIssue(Issue issue) {
        issue.setAddTime(new Date());
        issue.setDeleted(false);
        issue.setUpdateTime(new Date());
        int i = issueMapper.insertIssue(issue);
        return issue;
    }

    @Override
    public Issue updateIssue(Issue record) {
        record.setUpdateTime(new Date());
        int i = issueMapper.updateByPrimaryKey(record);
        return record;
    }

    @Override
    public void deleteIssue(Integer id) {
        int i = issueMapper.deleteByPrimaryKey(id);
    }
}

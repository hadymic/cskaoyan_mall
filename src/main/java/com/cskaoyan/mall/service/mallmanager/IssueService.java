package com.cskaoyan.mall.service.mallmanager;

import com.cskaoyan.mall.util.Page;

/**
 * @author jszza
 */
public interface IssueService {
    /**
     * 分页查询通用问题
     * @param page
     * @return
     */
    Object queryIssueList(Page page);
}

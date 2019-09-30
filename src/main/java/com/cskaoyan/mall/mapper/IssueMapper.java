package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Issue;

import java.util.List;

public interface IssueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Issue record);

    int insertSelective(Issue record);

    Issue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Issue record);

    int updateByPrimaryKey(Issue record);

    /**
     * 分页查询通用问题
     * @return
     */
    List<Issue> queryIssueList();
}

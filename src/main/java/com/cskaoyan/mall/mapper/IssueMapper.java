package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Issue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IssueMapper {
    /**
     * 删除issue
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    int insert(Issue record);

    int insertSelective(Issue record);

    Issue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Issue record);
    /**
     * 更新issue
     * @param record
     * @return
     */
    int updateByPrimaryKey(Issue record);


    /**
     * 分页查询通用问题
     * @param question
     * @return
     */
    List<Issue> queryIssueList(@Param("question") String question);

    /**
     * 创建issue
     * @param issue
     * @return
     */
    int insertIssue(@Param("issue")Issue issue);
}

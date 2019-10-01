package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.SearchHistory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SearchHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SearchHistory record);

    int insertSelective(SearchHistory record);

    SearchHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SearchHistory record);

    int updateByPrimaryKey(SearchHistory record);

    List<SearchHistory> selectByUserIdAndKeyword(@Param("userId")String userId,
                                                 @Param("keyword")String keyword);
}

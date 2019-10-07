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

    List<SearchHistory> selectByUserIdAndKeyword(@Param("userId") String userId,
                                                 @Param("keyword") String keyword);

    /**
     * Zeng-jz
     * 查询历史搜索记录
     *
     * @param userId
     * @return
     */
    List<SearchHistory> selectHistoryKeywordList(@Param("uid") int userId);

    /**
     * Zeng-jz
     * 根据userId删除历史搜索
     *
     * @param userId
     * @return
     */
    int deleteByUserId(@Param("uid") int userId);

    //去重
    int selectUniqueHistory(@Param("userId") Integer userId, @Param("keyword") String keyword);
}

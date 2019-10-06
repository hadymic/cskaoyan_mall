package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Keyword;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeywordMapper {
    /**
     * jszza:删除keyword
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    int insert(Keyword record);

    int insertSelective(Keyword record);

    Keyword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Keyword record);

    /**
     * jszza:更新keyword
     * @param record
     * @return
     */
    int updateByPrimaryKey(Keyword record);


    /**
     * jszza:查询关键字
     * @param keyword
     * @param url
     * @return
     */
    List<Keyword> queryKeywordList(@Param("keyword") String keyword, @Param("url")String url);

    /**
     * jszza:创建keyword
     * @param record
     * @return
     */
    int insertKeyWord(@Param("record") Keyword record);

    /**
     * Zeng-jz
     * @return
     */
    List<Keyword> selectHotKeyword();

    /**
     * Zeng-jz
     * 搜索默认keyword
     * @return
     */
    List<Keyword> selectDefaultKeyword();

    /**
     * Zeng-jz
     * @param key
     * @return
     */
    List<Keyword> selectHotByKeyword(@Param("key") String key);
}

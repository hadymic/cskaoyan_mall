package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.vo.goodsMangement.BaseValueLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    List<Category> selectAll();

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * categoryList
     * @param pid
     * @return
     */
    List<BaseValueLabel>  selectCategory(@Param("pid") int pid);

    void updateDeletedById(Category record);

}

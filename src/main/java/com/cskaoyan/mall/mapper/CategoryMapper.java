package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.vo.BaseValueLabel;
import com.cskaoyan.mall.vo.wx.home.FloorGoodsVo;
import org.apache.ibatis.annotations.Param;

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

    int updateDeletedById(Category record);

    /**
     * Zeng-jz: 增加商品
     * @param category
     * @return
     */
    int insertNewCategory(@Param("newCategory") Category category);

    /**
     * @author hjl 根据pid返回categoryList
     * @param pid
     * @return
     */
    List<Category> selectCategoryListByPid(@Param("pid") int pid);
    /**
     * Zeng-jz: 查询L1级别的id name iconUrl
     * @return
     */
    List<Category> selectChannel();

    List<Goods> selectFloorGoodsList(@Param("limit") int floorGoodsListSize, @Param("pid") int id);

    List<FloorGoodsVo> selectFloorList(@Param("limit") int floorListSize);

}

package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.vo.BaseValueLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    Brand selectByPrimaryKey(Integer id);

    /**
     * Zeng-jz: 返回所有未删除的品牌商信息
     * @param id
     * @param name
     * @return
     */
    List<Brand> selectAll(String id, String name);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    /**
     * 查找商标（brand)
     * @author hjl
     */
    List<BaseValueLabel> selectBrandList();


    /**
     * Zeng-jz: 增加新的品牌商
     * @param brand
     * @return
     */
    int insertNewBrand(@Param("newBrand") Brand brand);

    /**
     * Zeng-jz: 根据id查询品牌商
     * @param id
     * @return
     */
    Brand selectById(@Param("id") int id);

    /**
     * @author hjl 查找所有brand
     * @return
     */
    List<Brand> selectAllBrand();
}

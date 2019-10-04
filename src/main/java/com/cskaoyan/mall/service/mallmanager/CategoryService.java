package com.cskaoyan.mall.service.mallmanager;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.vo.wx.home.FloorGoodsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商场Service--商品类目
 *
 * @author Zeng-jz
 */
@Service
public interface CategoryService {
    List list();

    List l1();

    int update(Category category);

    void delete(Category category);

    Category create(Category category);

    List<Category> queryChannel();

    List<FloorGoodsVo> selectFloorGoodsList(int floorListSize, int floorGoodsListSize);
}

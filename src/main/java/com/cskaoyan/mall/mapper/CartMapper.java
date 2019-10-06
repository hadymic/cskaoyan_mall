package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    /**
     * 根据用户id查询购物车列表
     *
     * @param userId
     * @return
     */
    List<Cart> queryByUserId(@Param("userId") int userId, @Param("checked") boolean checked);

    /**
     * 根据商品id更新
     *
     * @param cart
     * @return
     */
    int updateByProductIdSelective(Cart cart);

    /**
     * 根据productId查询购物车
     *
     * @param userId
     * @param productId
     * @return
     */
    Cart queryByProductId(@Param("userId") int userId, @Param("productId") int productId);

    int deleteByProductIdAndUserId(Integer productId, Integer userId);

    /**
     * 删除购物车中的商品
     * @param userId
     * @param checked
     * @return
     */
    int deleteByUserId(@Param("userId") int userId, @Param("checked") boolean checked);
}

package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.Comment;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.wx.collect.CollectDetail;
import com.cskaoyan.mall.vo.wx.collect.CollectResultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);

    List<Comment> selectByTwoId(@Param("userId")String userId,
                                @Param("valueId")String valueId);

    /**
     * 查询是否收藏过
     * @param userId
     * @param collect
     * @return
     */
    Collect queryForUpdate(@Param("userId") Integer userId, @Param("collect") Collect collect);

    /**
     *返回collect的list
     * @param userId
     * @param type
     * @return
     */
    List<CollectDetail> queryCollectList(int userId, Integer type);
}

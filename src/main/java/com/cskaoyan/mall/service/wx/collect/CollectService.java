package com.cskaoyan.mall.service.wx.collect;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.wx.collect.CollectDetail;
import com.cskaoyan.mall.vo.wx.collect.CollectResultVo;

import java.util.Map;

/**
 * @author jszza
 */
public interface CollectService {

    /**
     * 添加或删除收藏
     * @param userId
     * @param collect
     * @return
     */
    Map insertOrDelete(Integer userId, Collect collect);

    /**
     * 返回collect的list
     * @param page
     * @param userId
     * @param type
     * @return
     */
    CollectResultVo<CollectDetail> queryCollectList(Page page, int userId, Integer type);
}

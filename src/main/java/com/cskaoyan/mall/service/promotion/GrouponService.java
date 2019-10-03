package com.cskaoyan.mall.service.promotion;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.promotion.GrouponVo;

public interface GrouponService {
    ListBean<GrouponRules> queryGrouponRuless(Page page, Integer goodsId);

    GrouponRules insertGrouponRules(GrouponRules grouponRules);

    GrouponRules updateGrouponRules(GrouponRules grouponRules);

    boolean deleteGrouponRules(Integer id);

    ListBean<GrouponVo> queryGrouponVo(Page page, Integer goodsId);
}

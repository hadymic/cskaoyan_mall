package com.cskaoyan.mall.service.promotion;

import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.GrouponVo;

public interface GrouponService {
    ListBean<GrouponRules> queryGrouponRuless(Page page, Integer goodsId);

    String insertGrouponRules(GrouponRules grouponRules);

    String updateGrouponRules(GrouponRules grouponRules);

    boolean deleteGrouponRules(Integer id);

    ListBean<GrouponVo> queryGrouponVo(Page page, Integer goodsId);
}

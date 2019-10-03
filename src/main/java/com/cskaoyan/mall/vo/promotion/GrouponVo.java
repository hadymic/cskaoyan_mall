package com.cskaoyan.mall.vo.promotion;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;

import java.util.List;

/**
 * 团购活动返回vo
 *
 * @author hadymic
 */
public class GrouponVo {
    private Goods goods;
    private Groupon groupon;
    private GrouponRules rules;
    private List<SubGrouponsVo> subGroupons;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    public GrouponRules getRules() {
        return rules;
    }

    public void setRules(GrouponRules rules) {
        this.rules = rules;
    }

    public List<SubGrouponsVo> getSubGroupons() {
        return subGroupons;
    }

    public void setSubGroupons(List<SubGrouponsVo> subGroupons) {
        this.subGroupons = subGroupons;
    }
}

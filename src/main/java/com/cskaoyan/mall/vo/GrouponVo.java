package com.cskaoyan.mall.vo;

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
    private List subGroupons;
}

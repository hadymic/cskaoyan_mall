package com.cskaoyan.mall.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageUtils {
    public static <T> ListBean<T> page(Page page, List<T> list) {
        PageHelper.startPage(page.getPage(), page.getLimit(), page.getSort() + " " + page.getOrder());
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new ListBean<>(pageInfo.getList(), list.size());
    }
}

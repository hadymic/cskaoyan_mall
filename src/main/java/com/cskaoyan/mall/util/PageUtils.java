package com.cskaoyan.mall.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 对PageHelper工具的封装类
 *
 * @author hadymic
 */
public class PageUtils {
    public static void startPage(Page page) {
        PageHelper.startPage(page.getPage(), page.getLimit(), page.getSort() + " " + page.getOrder());
    }

    public static <T> ListBean<T> page(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new ListBean<>(pageInfo.getList(), pageInfo.getTotal());
    }
}

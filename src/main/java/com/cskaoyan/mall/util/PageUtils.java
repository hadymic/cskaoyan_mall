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
        if (page.getSort() == null && page.getOrder() == null) {
            PageHelper.startPage(page.getPage(), page.getSize());
        } else {
            PageHelper.startPage(page.getPage(), page.getLimit(), page.getSort() + " " + page.getOrder());
        }
    }

    public static <T> ListBean<T> page(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new ListBean<>(pageInfo.getList(), pageInfo.getTotal());
    }

    public static <T> WxListBean<T> wxPage(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new WxListBean<>(pageInfo.getList(), pageInfo.getTotal());
    }


    public static <T> WxFootPrintListBean<T> wxFootPrintPage(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return new WxFootPrintListBean<>(pageInfo.getList(), pageInfo.getTotal());
    }

}

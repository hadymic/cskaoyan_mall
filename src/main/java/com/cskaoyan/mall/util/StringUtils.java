package com.cskaoyan.mall.util;

/**
 * 字符串工具类
 */
public class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static String addPercent(String str) {
        if (!StringUtils.isEmpty(str)) {
            str = "%" + str.trim() + "%";
        } else {
            str = null;
        }
        return str;
    }
}

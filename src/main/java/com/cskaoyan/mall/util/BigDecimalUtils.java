package com.cskaoyan.mall.util;

import java.math.BigDecimal;

/**
 * BigDecimal工具类
 */
public class BigDecimalUtils {

    /**
     * 判断是否为负数
     * true为负数
     * false不为负数
     *
     * @param val
     * @return
     */
    public static boolean isNegative(BigDecimal val) {
        return val.compareTo(BigDecimal.ZERO) < 0;
    }
}

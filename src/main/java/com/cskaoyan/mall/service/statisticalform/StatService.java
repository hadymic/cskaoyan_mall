package com.cskaoyan.mall.service.statisticalform;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 统计报表的Service
 *
 * @author Zeng-jz
 */
@Service
public interface StatService {
    Map user();

    Map<String, Object> order();

    Map<String, Object> goods();
}

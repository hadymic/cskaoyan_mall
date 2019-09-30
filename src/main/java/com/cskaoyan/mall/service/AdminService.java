package com.cskaoyan.mall.service;

import org.springframework.stereotype.Service;

/**
 *
 *
 * @author hadymic
 */
@Service
public interface AdminService {
    void queryAdminByPage(int page, int limit);
}

package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author hadymic
 */
@Service
public interface AdminService {

    ListBean<Admin> queryAdmin(Page page,String username);


     Admin insertAdmin(Admin admin);


    int update(Admin admin);

    void delete(Admin admin);
}

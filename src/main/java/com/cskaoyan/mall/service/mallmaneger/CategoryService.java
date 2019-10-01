package com.cskaoyan.mall.service.mallmaneger;

import com.cskaoyan.mall.bean.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商场Service--商品类目
 *
 * @author Zeng-jz
 */
@Service
public interface CategoryService {
    List list();

    List l1();

    int update(Category category);

    void delete(Category category);
}

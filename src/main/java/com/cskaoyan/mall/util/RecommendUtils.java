package com.cskaoyan.mall.util;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.vo.wx.catalog.WxCurrentCategoryVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 增加推荐算法
 *
 * @author hadymic
 */
public class RecommendUtils {
    @Autowired
    private static CategoryMapper categoryMapper;

    public static void addCategoryList(List<Category> channel) {
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        if (userId != null) {
            Category category = new Category();
            category.setId(-2);
            category.setIconUrl("http://r.photo.store.qq.com/psb?/V13FtNM83hjiAF/O7U14tUL2T*BvbbQaUN4qLiUHUPS48xVZg2MmgacT0M!/r/dLYAAAAAAAAA");
            category.setPicUrl("https://yanxuan.nosdn.127.net/5285e3a426ef5e3536281d33069ed0ff.jpg?imageView&quality=95&thumbnail=1920x420");
            category.setLevel("L1");
            category.setName("推荐");
            category.setSortOrder((byte) 1);
            channel.add(0, category);
        }
    }

    public static Category getCategory() {
        Category category = new Category();
        category.setId(-2);
        category.setIconUrl("http://r.photo.store.qq.com/psb?/V13FtNM83hjiAF/O7U14tUL2T*BvbbQaUN4qLiUHUPS48xVZg2MmgacT0M!/r/dLYAAAAAAAAA");
        category.setPicUrl("https://yanxuan.nosdn.127.net/5285e3a426ef5e3536281d33069ed0ff.jpg?imageView&quality=95&thumbnail=1920x420");
        category.setLevel("L1");
        category.setName("推荐");
        category.setSortOrder((byte) 1);
        return category;
    }

}

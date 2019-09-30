package com.cskaoyan.mall.service.mallmanager;

import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

/**
 * @author jszza
 */
public interface KeywordService {

    /**
     * 分页查询关键字
     * @param page
     * @param keyword
     * @param url
     * @return
     */
    ListBean<Keyword> queryKeywordList(Page page, String keyword, String url);

    /**
     * 创建keyword
     * @param keyword
     * @return
     */
    Keyword insertKeyword(Keyword keyword);
    /**
     * 更新keyword
     * @param keyword
     * @return
     */
    Keyword updateKeyword(Keyword keyword);

    /**
     * 删除keyword
     * @param id
     */
    void deleteKeyword(Integer id);
}

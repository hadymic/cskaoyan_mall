package com.cskaoyan.mall.service.mallmanager.impl;

import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.cskaoyan.mall.service.mallmanager.KeywordService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author jszza
 */
@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    KeywordMapper keywordMapper;
    @Override
    public ListBean<Keyword> queryKeywordList(Page page, String keyword, String url) {
        PageUtils.startPage(page);
        List<Keyword> orderList= keywordMapper.queryKeywordList("%"+keyword+"%","%"+url+"%");
        return PageUtils.page(orderList);
    }

    @Override
    public Keyword insertKeyword(Keyword keyword) {
        keyword.setAddTime(new Date());
        keyword.setUpdateTime(new Date());
        int i = keywordMapper.insertKeyWord(keyword);
        return keyword;
    }

    @Override
    public Keyword updateKeyword(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        int i = keywordMapper.updateByPrimaryKeySelective(keyword);
        return keyword;
    }

    @Override
    public void deleteKeyword(Integer id) {
        int i = keywordMapper.deleteByPrimaryKey(id);
    }
}

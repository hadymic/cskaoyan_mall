package com.cskaoyan.mall.service.wx.search;

import com.cskaoyan.mall.vo.wx.search.WxSearchVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WxSearchService {
    WxSearchVo index();

    List<String> selectHotByKerword(String keyword);

    boolean deleltedHistoryKeyword();
}

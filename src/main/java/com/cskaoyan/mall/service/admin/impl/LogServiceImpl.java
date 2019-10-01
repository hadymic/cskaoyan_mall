package com.cskaoyan.mall.service.admin.impl;

import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.mapper.LogMapper;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogMapper logMapper;
    @Override
    public ListBean<Log> queryLogs(Page page, String admin) {
        PageUtils.startPage(page);
        List<Log> logs= logMapper.queryLogs(admin);
        return PageUtils.page( logs);
    }
}

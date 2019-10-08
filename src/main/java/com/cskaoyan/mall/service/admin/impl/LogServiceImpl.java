package com.cskaoyan.mall.service.admin.impl;

import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.mapper.LogMapper;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.util.PageUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class LogServiceImpl implements LogService {
    @Autowired
    LogMapper logMapper;

    /**
     * 操作日志
     * 分页&查找
     * @param page
     * @param admin
     * @return
     */
    @Override
    public ListBean<Log> queryLogs(Page page, String admin) {
        PageUtils.startPage(page);

        List<Log> logs= logMapper.queryLogs(admin);

        return PageUtils.page( logs);
    }

    /**
     * 记录log，存到数据库
     *
     * @param type
     * @param action
     * @param status
     */
    @Override
    public void log(int type, String action, boolean status) {
        Subject subject = SecurityUtils.getSubject();
        Log log = new Log();
        log.setAdmin((String) subject.getPrincipal());
        log.setIp((String) subject.getSession().getAttribute("ip"));
        log.setType(type);
        log.setAction(action);
        log.setStatus(status);
        if (status) {
            log.setResult(action + "成功");
        } else {
            log.setResult(action + "失败");
        }
        Date date = new Date();
        log.setAddTime(date);
        log.setUpdateTime(date);
        log.setDeleted(false);

        logMapper.insertSelective(log);
    }
}

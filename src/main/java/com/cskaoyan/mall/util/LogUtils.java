package com.cskaoyan.mall.util;

import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.mapper.LogMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 记录log工具类
 */
public class LogUtils {
    @Autowired
    private static LogMapper logMapper;

    /**
     * 记录log，存到数据库
     *
     * @param type
     * @param action
     * @param status
     */
    public static void log(int type, String action, boolean status) {
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

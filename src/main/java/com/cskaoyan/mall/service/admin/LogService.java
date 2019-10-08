package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;

public interface LogService {
    ListBean<Log> queryLogs(Page page, String admin);

    void log(int type, String action, boolean status);
}

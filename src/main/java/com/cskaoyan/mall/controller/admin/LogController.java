package com.cskaoyan.mall.controller.admin;


import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.util.ListBean;
import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * 显示&查找
     *
     * @param page
     * @param log
     * @return
     */
    @RequestMapping("admin/log/list")
    public BaseRespVo log(Page page, Log log) {
        ListBean<Log> logs = logService.queryLogs(page, log.getAdmin());
        return BaseRespVo.success(logs);
    }

}

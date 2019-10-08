package com.cskaoyan.mall.controller.auth;

import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.service.auth.HomeService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ChangePasswordVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jszza
 */
@RestController
@RequestMapping("admin")
public class HomeController {
    @Autowired
    private HomeService homeService;
    @Autowired
    private LogService logService;

    /**
     * 首页显示
     *
     * @return
     */
    @RequestMapping("dashboard")
    public BaseRespVo homeInfo() {
        return BaseRespVo.success(homeService.queryHomeInfo());
    }

    /**
     * 修改密码
     *
     * @param changePasswordVo
     * @return
     */
    @RequestMapping("profile/password")
    public BaseRespVo updatePassword(@RequestBody ChangePasswordVo changePasswordVo) {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String msg = homeService.updatePassword(changePasswordVo, token);
        if (msg == null) {
            logService.log(1, "修改密码", true);
            return BaseRespVo.success(null);
        } else {
            logService.log(1,"修改密码",false);
            return BaseRespVo.fail(msg);
        }
    }
}

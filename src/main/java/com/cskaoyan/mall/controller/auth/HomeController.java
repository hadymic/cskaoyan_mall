package com.cskaoyan.mall.controller.auth;

import com.cskaoyan.mall.service.auth.HomeService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ChangePasswordVo;
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
    HomeService homeService;

    /**
     * 首页显示
     * @return
     */
    @RequestMapping("dashboard")
    public BaseRespVo orderList(){
        return BaseRespVo.success(homeService.queryHomeInfo());
    }

    /**
     * 修改密码
     * @param changePasswordVo
     * @return
     */
//    @RequestMapping("profile/password")
//    public BaseRespVo ship(@RequestBody ChangePasswordVo changePasswordVo){
        //return BaseRespVo.success(homeService.updatePassword(changePasswordVo));
//    }
}

package com.cskaoyan.mall.controller.wx.user;

import com.cskaoyan.mall.service.userserver.UserManageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.user.UserIndexVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jszza
 */
@RestController
@RequestMapping("wx/user")
public class WxUserController {
    @Autowired
    private UserManageService userManageService;

    @RequestMapping("index")
    public BaseRespVo userIndex() {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userId");
        if (userId == null) {
            return BaseRespVo.fail("请重新登录");
        }
        UserIndexVo vo = userManageService.queryUserOrders(token);
        Map map = new HashMap<>(1);
        map.put("order", vo);
        return BaseRespVo.success(map);
    }

}

package com.cskaoyan.mall.service.auth;

import com.cskaoyan.mall.vo.ChangePasswordVo;
import com.cskaoyan.mall.vo.HomeVo;

/**
 * @author jszza
 */
public interface HomeService {
    /**
     * 首页信息
     * @return
     */
    HomeVo queryHomeInfo();

    /**
     * 修改当前管理员密码
     * @param changePasswordVo
     * @param token
     * @return
     */
    String updatePassword(ChangePasswordVo changePasswordVo, String token);
}

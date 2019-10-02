package com.cskaoyan.mall.service.configManage;

import com.cskaoyan.mall.util.Page;
import com.cskaoyan.mall.vo.config.ExpressVo;
import com.cskaoyan.mall.vo.config.MallConfigVo;
import com.cskaoyan.mall.vo.config.OrderVo;
import com.cskaoyan.mall.vo.config.WxVo;

import java.util.Map;

public interface ConfigService {
    boolean experess(ExpressVo expressVo);
    Map experess();

    MallConfigVo getMall();
    boolean updateMall(MallConfigVo vo);

    OrderVo getOredr();

    boolean getOredr(OrderVo orderVo);

    WxVo getWx();

    boolean updateWx(WxVo wxVo);
}

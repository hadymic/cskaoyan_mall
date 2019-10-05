package com.cskaoyan.mall.service.configManage.impl;

import com.cskaoyan.mall.mapper.SystemMapper;
import com.cskaoyan.mall.service.configManage.ConfigService;
import com.cskaoyan.mall.util.StringUtils;
import com.cskaoyan.mall.util.TransformUtils;
import com.cskaoyan.mall.vo.config.ExpressVo;
import com.cskaoyan.mall.vo.config.MallConfigVo;
import com.cskaoyan.mall.vo.config.OrderVo;
import com.cskaoyan.mall.vo.config.WxVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;

@Component
@Transactional
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    SystemMapper systemMapper;
    @Override
    public boolean experess(ExpressVo expressVo) {
        int updatevalue = systemMapper.updateByKeyNema(expressVo.getCskaoyan_mall_express_freight_value(), "cskaoyan_mall_express_freight_value");
        int updatemin = systemMapper.updateByKeyNema(expressVo.getCskaoyan_mall_express_freight_min(), "cskaoyan_mall_express_freight_min");
        if (updatevalue*updatemin!=0) return true;
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return false;
    }
    @Override
    public Map experess() {
        Map map = new LinkedHashMap();
        map.put("cskaoyan_mall_express_freight_value",systemMapper.selectByKeyName("cskaoyan_mall_express_freight_value"));
        map.put("cskaoyan_mall_express_freight_min",systemMapper.selectByKeyName("cskaoyan_mall_express_freight_min"));
        return map;
    }

    @Override
    public MallConfigVo getMall() {
        MallConfigVo vo = new MallConfigVo();
        vo.setCskaoyan_mall_mall_address(systemMapper.selectByKeyName("cskaoyan_mall_mall_address"));
        vo.setCskaoyan_mall_mall_name(systemMapper.selectByKeyName("cskaoyan_mall_mall_name"));
        vo.setCskaoyan_mall_mall_phone(systemMapper.selectByKeyName("cskaoyan_mall_mall_phone"));
        vo.setCskaoyan_mall_mall_qq(systemMapper.selectByKeyName("cskaoyan_mall_mall_qq"));
        return vo;
    }

    @Override
    public boolean updateMall(MallConfigVo vo) {
        int address = systemMapper.updateByKeyNema(vo.getCskaoyan_mall_mall_address(), "cskaoyan_mall_mall_address");
        int name = systemMapper.updateByKeyNema(vo.getCskaoyan_mall_mall_name(), "cskaoyan_mall_mall_name");
        int phone = systemMapper.updateByKeyNema(vo.getCskaoyan_mall_mall_phone(), "cskaoyan_mall_mall_phone");
        int qq = systemMapper.updateByKeyNema(vo.getCskaoyan_mall_mall_qq(), "cskaoyan_mall_mall_qq");
        if (address*name*phone*qq!=0) return true;
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return false;
    }

    @Override
    public OrderVo getOredr() {
        OrderVo orderVo =new OrderVo();
        orderVo.setCskaoyan_mall_order_comment(systemMapper.selectByKeyName("cskaoyan_mall_order_comment"));
        orderVo.setCskaoyan_mall_order_unconfirm(systemMapper.selectByKeyName("cskaoyan_mall_order_unconfirm"));
        orderVo.setCskaoyan_mall_order_unpaid(systemMapper.selectByKeyName("cskaoyan_mall_order_unpaid"));
        return orderVo;
    }

    @Override
    @Transactional
    public boolean getOredr(OrderVo orderVo) {
        int comment = systemMapper.updateByKeyNema(orderVo.getCskaoyan_mall_order_comment(), "cskaoyan_mall_order_comment");
        int unconfirm = systemMapper.updateByKeyNema(orderVo.getCskaoyan_mall_order_unconfirm(), "cskaoyan_mall_order_unconfirm");
        int unpaid = systemMapper.updateByKeyNema(orderVo.getCskaoyan_mall_order_unpaid(), "cskaoyan_mall_order_unpaid");
        if (comment*unconfirm*unpaid!=0) return true;
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return false;
    }

    @Override
    public WxVo getWx() {
        WxVo wxVo = new WxVo();
        wxVo.setCskaoyan_mall_wx_catlog_goods(systemMapper.selectByKeyName("cskaoyan_mall_wx_catlog_goods"));
        wxVo.setCskaoyan_mall_wx_catlog_list(systemMapper.selectByKeyName("cskaoyan_mall_wx_catlog_list"));
        wxVo.setCskaoyan_mall_wx_index_brand(systemMapper.selectByKeyName("cskaoyan_mall_wx_index_brand"));
        wxVo.setCskaoyan_mall_wx_index_hot(systemMapper.selectByKeyName("cskaoyan_mall_wx_index_hot"));
        wxVo.setCskaoyan_mall_wx_index_new(systemMapper.selectByKeyName("cskaoyan_mall_wx_index_new"));
        wxVo.setCskaoyan_mall_wx_index_topic(systemMapper.selectByKeyName("cskaoyan_mall_wx_index_topic"));
        wxVo.setCskaoyan_mall_wx_share(TransformUtils.transformBoolean(systemMapper.selectByKeyName("cskaoyan_mall_wx_share")));
        return wxVo;
    }


    @Override
    @Transactional
    public boolean updateWx(WxVo wxVo) {
        int goods = systemMapper.updateByKeyNema(wxVo.getCskaoyan_mall_wx_catlog_goods(), "cskaoyan_mall_wx_catlog_goods");
        int list = systemMapper.updateByKeyNema(wxVo.getCskaoyan_mall_wx_catlog_list(), "cskaoyan_mall_wx_catlog_list");
        int brand = systemMapper.updateByKeyNema(wxVo.getCskaoyan_mall_wx_index_brand(), "cskaoyan_mall_wx_index_brand");
        int hot = systemMapper.updateByKeyNema(wxVo.getCskaoyan_mall_wx_index_hot(), "cskaoyan_mall_wx_index_hot");
        int wxnew = systemMapper.updateByKeyNema(wxVo.getCskaoyan_mall_wx_index_new(), "cskaoyan_mall_wx_index_new");
        int topic = systemMapper.updateByKeyNema(wxVo.getCskaoyan_mall_wx_index_topic(), "cskaoyan_mall_wx_index_topic");
        int share = systemMapper.updateByKeyNema(TransformUtils.transformString(wxVo.isCskaoyan_mall_wx_share()), "cskaoyan_mall_wx_share");
        if (goods*list*brand*hot*wxnew*topic*share!=0) return true;
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        return false;
    }
}

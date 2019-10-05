package com.cskaoyan.mall.vo.config;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class MallConfigVo {
    @NotEmpty
    private String cskaoyan_mall_mall_address;
    @NotEmpty
    private String cskaoyan_mall_mall_name;
    @NotEmpty
    private String cskaoyan_mall_mall_phone;
    @NotEmpty
    private String cskaoyan_mall_mall_qq;

    public String getCskaoyan_mall_mall_address() {
        return cskaoyan_mall_mall_address;
    }

    public MallConfigVo setCskaoyan_mall_mall_address(String cskaoyan_mall_mall_address) {
        this.cskaoyan_mall_mall_address = cskaoyan_mall_mall_address;
        return this;
    }

    public String getCskaoyan_mall_mall_name() {
        return cskaoyan_mall_mall_name;
    }

    public MallConfigVo setCskaoyan_mall_mall_name(String cskaoyan_mall_mall_name) {
        this.cskaoyan_mall_mall_name = cskaoyan_mall_mall_name;
        return this;
    }

    public String getCskaoyan_mall_mall_phone() {
        return cskaoyan_mall_mall_phone;
    }

    public MallConfigVo setCskaoyan_mall_mall_phone(String cskaoyan_mall_mall_phone) {
        this.cskaoyan_mall_mall_phone = cskaoyan_mall_mall_phone;
        return this;
    }

    public String getCskaoyan_mall_mall_qq() {
        return cskaoyan_mall_mall_qq;
    }

    public MallConfigVo setCskaoyan_mall_mall_qq(String cskaoyan_mall_mall_qq) {
        this.cskaoyan_mall_mall_qq = cskaoyan_mall_mall_qq;
        return this;
    }
    public  boolean nonVoid(){
        if (cskaoyan_mall_mall_address.equals("")||
                cskaoyan_mall_mall_name.equals("")||
                cskaoyan_mall_mall_phone.equals("")||
                cskaoyan_mall_mall_qq.equals(""))
            return false;
        return true;

    }
}

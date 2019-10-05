package com.cskaoyan.mall.vo.config;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ExpressVo {
    @NotEmpty
    String cskaoyan_mall_express_freight_min;
    @NotEmpty
    String cskaoyan_mall_express_freight_value;

    public String getCskaoyan_mall_express_freight_min() {
        return cskaoyan_mall_express_freight_min;
    }

    public ExpressVo setCskaoyan_mall_express_freight_min(String cskaoyan_mall_express_freight_min) {
        this.cskaoyan_mall_express_freight_min = cskaoyan_mall_express_freight_min;
        return this;
    }

    public String getCskaoyan_mall_express_freight_value() {
        return cskaoyan_mall_express_freight_value;
    }

    public ExpressVo setCskaoyan_mall_express_freight_value(String cskaoyan_mall_express_freight_value) {
        this.cskaoyan_mall_express_freight_value = cskaoyan_mall_express_freight_value;
        return this;
    }
    public  boolean nonVoid(){
        if (cskaoyan_mall_express_freight_min.equals("")||
                cskaoyan_mall_express_freight_value.equals(""))
            return false;
        return true;
    }
}

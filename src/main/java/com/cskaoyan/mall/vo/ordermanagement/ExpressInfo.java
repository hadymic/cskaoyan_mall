package com.cskaoyan.mall.vo.ordermanagement;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author jszza
 */
public class ExpressInfo {

    private String logisticCode;

    private String shipperName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date shipTime;

    public String getLogisticCode() {
        return logisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        this.logisticCode = logisticCode;
    }

    public String getShipperName() {
        return shipperName;
    }

    public void setShipperName(String shipperName) {
        this.shipperName = shipperName;
    }

    public Date getShipTime() {
        return shipTime;
    }

    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }
}

package com.cskaoyan.mall.vo.wx.cart;

import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.bean.Address;

import java.math.BigDecimal;
import java.util.List;

/**
 * checkout返回类
 */
public class CartCheckoutReturnVo {
    private List<Cart> checkedGoodsList;
    private Address checkedAddress;
    //可用的优惠券数量
    private int availableCouponLength;
    //实际需要支付的总价
    private BigDecimal actualPrice;
    //优惠券的价格
    private BigDecimal couponPrice;
    //团购优惠价格
    private BigDecimal grouponPrice;
    //快递费
    private BigDecimal freightPrice;
    //商品总价
    private BigDecimal goodsTotalPrice;
    //订单总价
    private BigDecimal orderTotalPrice;
    private int addressId;
    private int couponId;
    private int grouponRulesId;

    public List<Cart> getCheckedGoodsList() {
        return checkedGoodsList;
    }

    public void setCheckedGoodsList(List<Cart> checkedGoodsList) {
        this.checkedGoodsList = checkedGoodsList;
    }

    public Address getCheckedAddress() {
        return checkedAddress;
    }

    public void setCheckedAddress(Address checkedAddress) {
        this.checkedAddress = checkedAddress;
    }

    public int getAvailableCouponLength() {
        return availableCouponLength;
    }

    public void setAvailableCouponLength(int availableCouponLength) {
        this.availableCouponLength = availableCouponLength;
    }

    public BigDecimal getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getGrouponPrice() {
        return grouponPrice;
    }

    public void setGrouponPrice(BigDecimal grouponPrice) {
        this.grouponPrice = grouponPrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getGoodsTotalPrice() {
        return goodsTotalPrice;
    }

    public void setGoodsTotalPrice(BigDecimal goodsTotalPrice) {
        this.goodsTotalPrice = goodsTotalPrice;
    }

    public BigDecimal getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getGrouponRulesId() {
        return grouponRulesId;
    }

    public void setGrouponRulesId(int grouponRulesId) {
        this.grouponRulesId = grouponRulesId;
    }
}

package com.cskaoyan.mall.bean;

import java.util.Date;


public class Address {
    private Integer id;

    private String name;

    private Integer userId;

    private Integer provinceId;

    private Integer cityId;

    private Integer areaId;

    private String address;

    private String mobile;

    private Boolean isDefault;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;

    private String province;

    private String provinceName;

    private String city;

    private String cityName;

    private String area;

    private String areaName;

    public String getProvinceName() {
        return provinceName;
    }

    public Address setProvinceName(String provinceName) {
        this.provinceName = provinceName;
        return this;
    }

    public String getCityName() {
        return cityName;
    }

    public Address setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String getAreaName() {
        return areaName;
    }

    public Address setAreaName(String areaName) {
        this.areaName = areaName;
        return this;
    }

    private String detailedAddress;

    public String getDetailedAddress() {
        return province + city + area + address;
    }

    public String getOrderAddress() {
        return provinceName + cityName + areaName + address;
    }

    public Address setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
        return this;
    }

    public String getArea() {
        return area;
    }

    public Address setArea(String area) {
        this.area = area;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public Address setProvince(String province) {
        this.province = province;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public Address setDefault(Boolean aDefault) {
        isDefault = aDefault;
        return this;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}

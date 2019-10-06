package com.cskaoyan.mall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * oss存储
 */
@Component
@ConfigurationProperties(prefix = "mall.aliyun.oss")
public class MallOssConfig {
    private String bucket;
    private String endPoint;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
}
package com.cskaoyan.mall.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 阿里云总配置
 */
@Component
@ConfigurationProperties(prefix = "mall.aliyun")
public class AliyunConfig {
    private String accessKeyId;
    private String accessSecret;
    @Autowired
    private MallOssConfig ossConfig;
    @Autowired
    private SmsConfig smsConfig;

    public MallOssConfig getOssConfig() {
        return ossConfig;
    }

    public void setOssConfig(MallOssConfig ossConfig) {
        this.ossConfig = ossConfig;
    }

    public SmsConfig getSmsConfig() {
        return smsConfig;
    }

    public void setSmsConfig(SmsConfig smsConfig) {
        this.smsConfig = smsConfig;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessSecret() {
        return accessSecret;
    }

    public void setAccessSecret(String accessSecret) {
        this.accessSecret = accessSecret;
    }
}

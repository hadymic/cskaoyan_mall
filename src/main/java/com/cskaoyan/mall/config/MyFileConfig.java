package com.cskaoyan.mall.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.UUID;

@ConfigurationProperties(prefix = "myfile.config")
@Component
public class MyFileConfig {
    //需要存储的前缀路径（默认为target路径）
    private String targetPath = this.getClass().getClassLoader().getResource("").getPath() + "static/";
    //网络路径
    private String netPath = "http://localhost:80/";
    //图片路径
    private String picPath = "pic";

    private String hashPath;

    /**
     * 传入一个文件名，返回一个经过UUID处理的文件名
     * 并将hashPath自动生成
     *
     * @param fileName
     * @return
     */
    public String hashUUID(String fileName) {
        //使用UUID来防止重名文件
        fileName = UUID.randomUUID().toString().replace("-", "") + "-" + fileName;

        StringBuilder pathBuilder = new StringBuilder();
        //使用散列分散文件
        String s = Integer.toHexString(fileName.hashCode()).substring(4);
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            pathBuilder.append("/").append(aChar);
        }
        hashPath = pathBuilder.append("/").toString();
        return fileName;
    }

    /**
     * 返回一个完整的文件路径
     *
     * @return
     */
    public String getCompleteTargetPath() {
        return targetPath + picPath + hashPath;
    }

    /**
     * 返回一个完整的网络路径
     *
     * @return
     */
    public String getCompleteNetPath() {
        return netPath + picPath + hashPath;
    }

    /**
     * 解析图片路径，去除网络路径前缀，方便存储数据库
     *
     * @param url
     * @return
     */
    public String parsePicUrl(String url) {
        String path = netPath + picPath + "/";
        if (url.contains(path)) {
            url = url.replace(path, "");
        }
        return url;
    }

    /**
     * 添加网络路径前缀
     *
     * @param url
     * @return
     */
    public String addPicUrl(String url) {
        if (url.contains("http://yanxuan.nosdn.127.net/")) {
            return url;
        }
        String path = netPath + picPath + "/";
        return path + url;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getNetPath() {
        return netPath;
    }

    public void setNetPath(String netPath) {
        this.netPath = netPath;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getHashPath() {
        return hashPath;
    }

    public void setHashPath(String hashPath) {
        this.hashPath = hashPath;
    }
}

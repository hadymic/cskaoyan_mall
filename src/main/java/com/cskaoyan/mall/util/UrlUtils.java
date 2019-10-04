package com.cskaoyan.mall.util;


import com.cskaoyan.mall.config.MyFileConfig;


/**
 * String[] 图片url的前缀后缀处理
 * @author stark_h
 */
public class UrlUtils {
    private static MyFileConfig myFileConfig = new MyFileConfig();

    public static String[] CheckListUrls(String[] oldUrls, boolean b) {
        if (oldUrls == null) return null;
        int len = oldUrls.length;
        String[] newUrls = null;

        for (String oldUrl : oldUrls) {
            String addUrl = null;
            if (b) {
                addUrl = myFileConfig.addPicUrl(oldUrl);//真，增加图片前缀
            } else {
                addUrl = myFileConfig.parsePicUrl(oldUrl);//假，去除图片前缀
            }
            if (newUrls == null) {
                newUrls = new String[len];
            }
            for (int i = 0; i < len; i++) {
                newUrls[i] = addUrl;
            }
        }
        return newUrls;
    }
}

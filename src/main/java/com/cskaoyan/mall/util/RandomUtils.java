package com.cskaoyan.mall.util;

import java.util.Random;

/**
 * 获取指定长度的随机数
 * @author jszza
 */
public class RandomUtils {
    public static String getRandom(int len){
        Random r = new Random();
        int bitNum=1;
        for(int i=0;i<len;i++){
            bitNum=bitNum*10;
        }
        long num = Math.abs(r.nextLong() % (bitNum));
        StringBuilder s = new StringBuilder(String.valueOf(num));
        for (int i = len - s.length(); i >0 ;i--) {
            s.insert(0, "0");
        }
        if(s.length()>len){
            s = new StringBuilder(s.substring(0, len));
        }
        return s.toString();
    }
}


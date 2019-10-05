package com.cskaoyan.mall.util;

public class TransformUtils {
    //把一个 boolean类型的数据转换成String样式
    public static String  transformString(boolean b){
        if (b) return "true";
        return "false";
    }
    //把String类型的"ture"转换成boolean类型
    public static boolean transformBoolean(String str){
        if ("true".equals(str))return true;
        return false;
    }
    //把一个boolean类型的数据转换成byte,ture为1，false为0
    public static byte transformByte(boolean b){
        if (b) return 1;
        else return 0;
    }
    public static boolean transformBoolean(int i){
        if (i!=0) return true;
        else return false;
    }
}

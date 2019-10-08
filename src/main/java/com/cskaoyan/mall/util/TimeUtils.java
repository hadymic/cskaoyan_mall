package com.cskaoyan.mall.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jszza
 */
public class TimeUtils {

    public static Date getEndMinutes(int addMinutes){
        long currentTime = System.currentTimeMillis() ;
        currentTime += 30 * 60 * 1000;
        return new Date(currentTime);
    }


    public static Date getEndDay(int addDays){
        Calendar curr = Calendar.getInstance();
        curr.add(Calendar.DATE, addDays);
        return curr.getTime();
    }

    public static String getDateString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }
}

package com.mirai.indidea.utils;

import java.util.Date;

public class DateUtils {
    /**
     * 获取当前日期之后的时间
     * @param hours 多久之后
     * @return  指定之间之后的日期
     */
    public static synchronized Date getTime(double hours) {
        long currentTime = System.currentTimeMillis();
        currentTime += hours * 60 * 60 * 1000;
        Date date;
        date = new Date(currentTime);
        return date;
    }
}

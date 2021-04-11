package com.hhu.ts.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: WaterProject
 * @description: 类型转换主要是json和字符串和时间的转换
 * @author: Wang JinLei
 * @create: 2021-03-27 14:15
 */
public class TypeConversionUtil {
    /**
     * 去除字符串中的中括号
     *
     * @param stringWithSquareBrackets
     * @return
     */
    public static String removeBrackets(String stringWithSquareBrackets) {
        return stringWithSquareBrackets.substring(1, stringWithSquareBrackets.length() - 1);
    }

    /**
     * 将字符串转换成时间
     *
     * @param dateString
     * @return
     */
    public static Date toStringDate(String dateString) {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //使用SimpleDateFormat的parse()方法生成Date
            date = sf.parse(dateString);
            //打印Date
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将字符串转换成时间戳
     *
     * @param time
     * @return
     */
    public static String toStringTimestamp(String time) {
        String timestamp = null;

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long longTime = sdf.parse(time).getTime();
            timestamp = Long.toString(longTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    /**
     * @param timestamp
     * @return
     */
    public static String toStampDate(String timestamp) {
        String time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(timestamp);
        Date date = new Date(lt);
        time = simpleDateFormat.format(date);
        return time;
    }

}

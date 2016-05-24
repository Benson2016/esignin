package com.benson.esignin.common.utils;

import com.benson.esignin.common.cons.CommonCons;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Timestamp 工具类，提供常用的时间类型转换等功能
 *
 * @author Benson Xu
 * @version 1.0
 * @since 2016年05月12日 18:13
 */
public class TimestampUtil {

    private final static String dateFmtStr = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期时间
     * 时间格式：yyyy-MM-dd HH:mm:ss
     * @return 'yyyy-MM-dd HH:mm:ss'格式时间对象
     */
    public static Timestamp getCurrentTimestampWithFormat() {

        DateFormat df = new SimpleDateFormat(dateFmtStr);
        String currDateStr = df.format(new Date());
        Timestamp currentTime = Timestamp.valueOf(currDateStr);

        return currentTime;
    }

    /**
     * 获取指定格式的当前日期时间
     * @param format 时间格式
     * @return format格式时间对象
     */
    public static Timestamp getCurrentTimestampWithFormat(String format) {
        if (null == format || "".equals(format.trim())) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(format);
        String currDateStr = df.format(new Date());
        Timestamp currentTime = Timestamp.valueOf(currDateStr);

        return currentTime;
    }

    /**
     * 将字时间符串格式转化为[yyyy-MM-dd HH:mm:ss]格式的时间对象
     * 注意：time格式必须是 yyyy-MM-dd HH:mm:ss[.fffffffff]
     * @param time 时间字符串（如：2014-06-18 10:57:58）
     * @return Timestamp时间对象
     */
    public static Timestamp convertToTimestampByString(String time) {
        return convertToTimestampByString(time, CommonCons.D_FMT_NORMAL);
    }

    /**
     * 将字时间符串格式转化为目标格式的时间对象
     * @param time 时间字符串
     * @param format 目标时间格式
     * @return Timestamp时间对象
     */
    public static Timestamp convertToTimestampByString(String time, String format) {
        if (null == time || "".equals(time.trim())) {
            return null;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Timestamp timestamp = new Timestamp(sdf.parse(time).getTime());
            return timestamp;
        } catch (Exception e) {
            System.out.println("时间内容不合法！" + e.getMessage());
            return null;
        }
    }

    /**
     * 将时间转化为'yyyy-MM-dd HH:mm:ss'格式的字符串
     * @param timestamp 需转化的时间
     * @return 'yyyy-MM-dd HH:mm:ss'格式字符串
     */
    public static String convertToString(Timestamp timestamp) {
        return convertToStringWithFmt(timestamp, dateFmtStr);
    }

    /**
     * 将时间转化为指定格式的字符串
     * @param timestamp 需要转化的时间
     * @param format 想要转化的格式（如：yyyy-MM-dd HH:mm:ss或yyyy-MM-dd）
     * @return 转换后的格式字符串
     */
    public static String convertToStringWithFmt(Timestamp timestamp, String format) {
        if (null == timestamp || null == format || "".equals(format.trim())) {
            return null;
        }

        String formatStr = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        formatStr = sdf.format(timestamp);

        return formatStr;
    }

    /*public static void main(String[] args) {
        Timestamp s = new Timestamp(System.currentTimeMillis());
        String str = TimestampUtil.convertToString(s);
        System.out.println(s + "   " + str + "   " +getCurrentTimestampWithFormat());
    }*/

}

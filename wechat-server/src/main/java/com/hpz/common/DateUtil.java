package com.hpz.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by tommy on 15/5/6.
 */
public class DateUtil {

    private static final String DATESTRING = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获得默认格式的现在日期
     * @return
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 获得指定格式的现在日期
     * @return
     */
    public static Date now(String format) {
        return formatDate(format, now());
    }

    /**
     * 返回yyyyMMdd 格式的现在日期字符串
     * @return
     */
    public static  String formatterNow(){
        return format("yyyyMMdd", now());
    }

    /**
     * 按照指定的格式格式化日期
     * 如果格式化失败,则会返回默认格式的现在日期
     * @return
     */
    private static Date formatDate(String format, Date date) {
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern(format);
        formater.setLenient(true);
        try {
            return formater.parse(formater.format(date));
        } catch (ParseException e) {
            return now();
        }
    }

    /**
     * 返回yyyy-MM-dd 格式的日期字符串
     * @return
     */
    public static String formatYYYYMMDD(Date date) {
        return format("yyyy-MM-dd", date);
    }

    public static String formatDay(Date date){
        return format("dd",date);
    }

    /**
     * 返回yyyy-MM-dd HH:mm:ss 格式的日期字符串
     * @return
     */
    public static String formatYYYYMMDDHHMMSS(Date date) {
        return format("yyyy-MM-dd HH:mm:ss", date);
    }

    /**
     * 返回yyyy-MM-dd HH:mm 格式的日期字符串
     * @return
     */
    public static String formatYYYYMMDDHHMM(Date date) {
        return format("yyyy-MM-dd HH:mm", date);
    }

    /**
     * 返回指定格式的日期字符串
     * @param formatStr  日期格式
     * @param date   需要格式化的日期
     * @return
     */
    public static String format(String formatStr, Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern(formatStr);
        return formater.format(date);
    }

    /**
     * 将字符串转换成"yyyy-MM-dd HH:mm:ss"格式的日期
     * 字符串的格式：  2015-05-23 00:00:00
     * @param string
     * @return
     */
    public static Date StringToDate(String string) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formater.setLenient(true);
        try {
            return formater.parse(string);
        } catch (ParseException e) {
            throw new IllegalArgumentException("invalid date format. Please use this pattern\""+string
            +"\"");
        }

    }

     public static String  getSimpleDateStringAfterNow(int days){
           DateFormat df = new SimpleDateFormat("yyyyMMdd");
           Calendar c = Calendar.getInstance();
           c.add(Calendar.DAY_OF_YEAR, days);
           return df.format(c.getTime());
     }

    //!~for test
     public static void main(String[] args){
         System.out.println(getSimpleDateStringAfterNow(1));
         System.out.println(getSimpleDateStringAfterNow(2));
         System.out.println(getSimpleDateStringAfterNow(3));
     }

    public static Date parse(String dateString,String formatterString){
        DateFormat df = new SimpleDateFormat(formatterString);
        try {
            Date d = df.parse(dateString);
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断两个时间间隔  -1=没有意义|0=同一天|1=相差一天|2=大于等于两天
     * @param previous
     * @param next
     * @return
     */
    public static int compare2Days(Calendar previous,Calendar next){
        if(previous==null||next==null){
            return -1;
        }

        //同一年比较
        if(previous.get(Calendar.YEAR)==next.get(Calendar.YEAR)){

            if(next.get(Calendar.DAY_OF_YEAR)==previous.get(Calendar.DAY_OF_YEAR)){
                return 0;
            }else if(next.get(Calendar.DAY_OF_YEAR)-previous.get(Calendar.DAY_OF_YEAR)==1){
                return 1;
            }

        }
        return 2;

    }


}

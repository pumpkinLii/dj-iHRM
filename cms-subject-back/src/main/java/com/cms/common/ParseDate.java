package com.cms.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {
    //获取当前时间的
    public static Date getCurrentDate() throws ParseException {
        //获取当前日期的Date类型
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd");
        String format1 = simpleDateFormat.format(new Date());
        String[] s = format1.split(" ");
        Date parse = simpleDateFormat1.parse(s[0]);
        return parse;
    }
    public static String getCurrentTime(){
        //获取 当前的时间
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(new Date());
        String[] s = format1.split(" ");
        return s[1];
    }
    public static String getCurrentDatetoString() throws ParseException {
        //获取当前时间的 yyyy-MM-dd类型
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd");
        String format1 = simpleDateFormat.format(new Date());
        String[] s = format1.split(" ");
        return s[0];
    }
}

package com.cms.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseDate {
    //获取当前时间的
    public static Date getCurrentDate() throws ParseException {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(new Date());
        String[] s = format1.split(" ");
        Date parse = simpleDateFormat.parse(s[0]);
        return parse;
    }
    public static String getCurrentTime(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format1 = simpleDateFormat.format(new Date());
        String[] s = format1.split(" ");
        return s[1];
    }
    public static Date parse(String s) throws ParseException {
        //把前端传来的字符串转换为Date类型的数据
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM--dd");
        return simpleDateFormat.parse(s);
    }
}

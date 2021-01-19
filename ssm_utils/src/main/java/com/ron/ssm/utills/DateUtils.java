package com.ron.ssm.utills;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//日期转换
public class DateUtils {

    //转换成字符串
    public static String DateToString(Date date,String patt){
        SimpleDateFormat dateFormat = new SimpleDateFormat(patt);
        String format = dateFormat.format(date);
        return format;

    }


    //字符串转换成日期
    public static Date StringToDate(Date date, String patt) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(patt);
        Date parse = dateFormat.parse(patt);
        return parse;

    }

}

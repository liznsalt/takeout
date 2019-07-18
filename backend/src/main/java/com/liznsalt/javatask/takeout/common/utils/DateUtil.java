package com.liznsalt.javatask.takeout.common.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date formatTimeStamp(Timestamp timestamp) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = fmt.parse(timestamp.toString());
            String s = fmt.format(date);
            return fmt.parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    public static void main(String[] args) throws Exception{
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(fmt.parse("2019-06-24 12:33:17"));
        System.out.println(new Date());
    }
}

package com.zd1024.withyou.Util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class DateUtil {

    public static String getDateByDay(Date date) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("YYYY-mm-dd");
        return sdf1.format(date);
    }

    public static boolean checkDate(Date date, List<Date> dateList) {
        for (Date d : dateList) {
            if (getDateByDay(date).equals(getDateByDay(d))) {
                return false;
            }
        }
        return true;
    }
}

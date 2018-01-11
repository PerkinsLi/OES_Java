package com.perkins.oes.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static Date getNowDate() {
        Date now = new Date();
        return now;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdate = sdf.format(date);
        return nowdate;
    }
}

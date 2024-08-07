package com.newgen.iforms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

    private DateUtil() {

    }

    public static Date parseStringToDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(date, Locale.forLanguageTag(format));
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String parseDateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(String.valueOf(date), Locale.forLanguageTag(format));
        return sdf.format(date);
    }
}

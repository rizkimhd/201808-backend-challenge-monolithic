package com.rizkirm.challenge.bank.util;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class DateUtil {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static String dateToString(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    public static String dateToString(Date date) {
        return dateToString(date, YYYY_MM_DD);
    }

    public static Date stringToDate(String date, String format) {
        try {
            return new SimpleDateFormat(format).parse(date);
        } catch (ParseException e) {
            throw new CustomBadRequestException("Format Date " + format + " ex: " + dateToString(new Date()));
        }
    }

    public static Date stringToDate(String date) {
        return stringToDate(date, YYYY_MM_DD);
    }

    public static Date format(Date date, String format) {
        return stringToDate(dateToString(date, format), format);
    }

}
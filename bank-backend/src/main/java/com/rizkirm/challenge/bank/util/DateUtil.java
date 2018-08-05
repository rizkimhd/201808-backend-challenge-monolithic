package com.rizkirm.challenge.bank.util;

import com.rizkirm.challenge.bank.exception.CustomBadRequestException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rizkimuhammad on 05/08/18.
 */
public class DateUtil {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
    public static final String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    public static String dateToString(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String result = dateFormat.format(date);
        return result;
    }

    public static String dateToString(Date date) {
        return dateToString(date, YYYY_MM_DD);
    }

    public static Date stringToDate(String date, String format) {
        Date result;
        DateFormat dateFormat = new SimpleDateFormat(format);

        try {
            result = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new CustomBadRequestException("Format Date " + format + " ex: " + dateToString(new Date()));
        }

        return result;
    }

}
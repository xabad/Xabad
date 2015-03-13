// +----------------------------------------------------------------------
package com.lac.xab.utils;




import com.lac.xaboa.utils.V;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author xab
 *         时间类型格式化
 */
public class DateTimeUtils extends DateUtils {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");
    ;
    private static SimpleDateFormat timeFormat = new SimpleDateFormat(
            "HH:mm:ss");
    private static SimpleDateFormat dateTimeformat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    static Date date = null;

    /* yyyy-MM-dd */
    public static String StrYMD() {
        date = new Date();
        return DateFormatUtils.ISO_DATE_FORMAT.format(date);
    }

    /* hh-mm-ss */
    public static String StrHMS() {
        date = new Date();
        return DateFormatUtils.ISO_TIME_NO_T_FORMAT.format(date);
    }

    /* yyyy-MM-dd hh-mm-ss */
    public static String StrYMDHMS() {
        return StrYMD() + " " + StrHMS();
    }

    /**
     * ************************************************
     * Xab - 2013-7-8 下午3:40:20
     * Description
     * - 一般用于MySql数据库中的时间转换
     * - 传入的值为 long类型 10位数值
     * Return String
     * ************************************************
     */
    public static String getValueByTime(String time) {
        return getValueByTime(Long.valueOf(time), V.DATETIME);
    }

    public static String getValueByTime(long time) {
        return getValueByTime(time, V.DATETIME);
    }

    public static String getValueByTime(long time, int formatType) {
        String dateTemp = "";
        if (time != 0) {
            if (V.DATE == formatType) {
                dateTemp = dateFormat.format(new Time(time * 1000L));
            } else if (V.TIME == formatType) {
                dateTemp = timeFormat.format(new Time(time * 1000L));
            } else {
                dateTemp = dateTimeformat.format(new Time(time * 1000L));
            }
            return dateTemp;
        } else {
            return null;
        }
    }

    /**
     * ************************************************
     * Xab - 2013-7-8 下午3:45:56
     * Description
     * - 时间类型的转换
     * Return String
     * ************************************************
     */
    public static String formatToStr(Date paramDate) {
        return dateTimeformat.format(paramDate);
    }

    public static String formatDateToStr(Date paramDate) {
        return dateFormat.format(paramDate);
    }

    public static String formatTimeToStr(Date paramDate) {
        return timeFormat.format(paramDate);
    }

    public static Date getDateTimeByStr(String paramDate) {
        Date date = null;
        try {
            date = dateTimeformat.parse(paramDate);
        } catch (ParseException e) {
            date = new Date();
        }
        return date;
    }

    public static Date getDateByStr(String paramDate) {
        Date date = null;
        try {
            date = dateFormat.parse(paramDate);
        } catch (ParseException e) {
            date = new Date();
        }
        return date;
    }

    public static Date getTimeByStr(String paramDate) {
        Date date = null;
        try {
            date = timeFormat.parse(paramDate);
        } catch (ParseException e) {
            date = new Date();
        }
        return date;
    }


    /**
     * 得到前几天的long
     *
     * @param day
     * @return
     */
    public static Long getBeforeDay(int day) {
        long rval = 0L;
        long now = System.currentTimeMillis() / 1000;
        rval = now - day * 24 * 60 * 60;
        return rval;

    }

    /**
     * 得到后几天的long
     *
     * @param day
     * @return
     */
    public static Long getAfterDay(int day) {
        long rval = 0L;
        long now = System.currentTimeMillis() / 1000;
        rval = now + day * 24 * 60 * 60;
        return rval;
    }
}

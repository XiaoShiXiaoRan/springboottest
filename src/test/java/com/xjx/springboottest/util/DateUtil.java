package com.xjx.springboottest.util;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Stream;


public class DateUtil {

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_DAY = "yyyy/MM/dd";
    public static final String FORMAT_DATE_DAY_24 = "dd/MM/yyyy HH:mm:ss";



    public static final String FORMAT_TIME_24 = "HH:mm:ss";
    public static final String FORMAT_TIME_12 = "hh:mm:ss";
    public static final String FORMAT_DATE_TIME_24 = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_TIME_MM_24 = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE_TIME_12 = "yyyy-MM-dd hh:mm:ss";
    public static final String FORMAT_DATE_YYYYMMDD = "yyyyMMdd";
    public static final String FORMAT_DATE_YYYY = "yyyy";
    public static final String FORMAT_DATE_YYYYMMDDHHMMDD_24 = "yyyyMMddHHmmss";
    public static final String FORMAT_DATE_YYYYMMDDHHMMDD_12 = "yyyyMMddhhmmss";
    public static final String FORMAT_DATE_TIME_CN_24 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String FORMAT_DATE_YYMMDD = "yyMMdd";
    public static final String FORMAT_DATE_YYYYMM = "yyyy-MM";
    public static final String FORMAT_DATE_MM = "MM";
    public static final String FORMAT_DATE_DD = "dd";
    public static final String FORMAT_DATE_MM_DD = "MM-dd";
    public static final String FORMAT_DATE_HHMM = "HH:mm";
    public static final String FORMAT_DATE_YYMM = "yyMM";
    public static final int FIELD_YEAR = Calendar.YEAR;
    public static final int FIELD_MONTH = Calendar.MONTH;
    public static final int FIELD_DAY = Calendar.DAY_OF_MONTH;
    public static final int FIELD_HOUR = Calendar.HOUR_OF_DAY;
    public static final int FIELD_MINUTE = Calendar.MINUTE;
    public static final int FIELD_SECOND = Calendar.SECOND;

    public static final String START_SECOND = " 00:00:00" ;

    public static final String ENd_SECOND = " 23:59:59" ;


    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateData(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_DAY_24);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_YYYYMM);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 时间比较
     * <p>
     * 如果第一个时间大于第二时间返回1<br/>
     * </p>
     * @param firstTime
     * @param secondTime
     * @return
     */
    public static int compare(Date firstTime,Date secondTime){
        long diff =firstTime.getTime()- secondTime.getTime() ;
        if(diff>0){
            return 1;
        }else if(diff==0){
            return 0;
        }else{
            return -1;
        }
    }

    public static String format(Date date, String pattern) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        if (StringUtils.isEmpty(pattern)) {
            throw new NullPointerException("pattern is null");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date parse(String date, String pattern) {
        if (StringUtils.isEmpty(date)) {
            throw new NullPointerException("date is null");
        }
        if (StringUtils.isEmpty(pattern)) {
            throw new NullPointerException("pattern is null");
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(date);
        } catch (Exception e) {
            logger.error("Exception in DateUtil.parse ...", e);
        }
        return null;
    }

    /***
     *
     * 获取当前时间30天之前的时间
     */
    public  static   Date  getLast30Day(){
        Date now = new Date();
        Date startDate = DateUtils.addDays(now, -30);
        return startDate;
    }

    /**
     * 计算时间
     * @param date   基准时间
     * @param field  时间轴
     * @param amount 加减值
     * @return
     */
    public static Date calculateTime(Date date, int field, int amount) {
        if (date == null) {
            throw new NullPointerException("date is null");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 获取当前时期
     *
     * @return
     */
    public static Date getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    public static String getTodayDateStr() {
        return format(new Date(), FORMAT_DATE);
    }

    /**
     * 被比较时间是否在给定区间内
     *
     * @param compareDate
     * @param minDate
     * @param maxDate
     * @return true : minDate<=compareDate<=maxDate
     */
    public static boolean between(Date compareDate, Date minDate, Date maxDate) {
        return compareDate.compareTo(minDate) >= 0 && compareDate.compareTo(maxDate) <= 0;
    }

    public static Date getFormatDate(Date date) {
        if (date == null) {
            date = new Date();
        }
        SimpleDateFormat fm = new SimpleDateFormat(FORMAT_DATE_TIME_24);
        String d = fm.format(date);
        Date dateFm = null;
        try {
            dateFm = fm.parse(d);
        } catch (ParseException e) {
            logger.error("ParseException in DateUtil.parse ...", e);
        }
        return dateFm;
    }
    /**
     * 日期时间字符串转换成日期时间对象.
     * @param dtStr 日期时间字符串，不能为空
     * @param fmt 格式字符串，不能为空
     * @return 日期时间对象，参数为空或转换异常时返回null
     */
    public static Date str2Dt(String dtStr, String fmt)
    {
        if (StringUtils.isBlank(dtStr) || StringUtils.isBlank(fmt))
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        try
        {
            return sdf.parse(dtStr);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 日期时间对象转换成日期时间字符串.
     * @param dt 日期时间对象，为空则默认为当前日期时间
     * @param fmt 转换格式，为空则默认为“yyyyMMddHHmmss”
     * @return 日期时间字符串
     */
    public static String dt2Str(Date dt, String fmt)
    {
        String usedFmt = StringUtils.isBlank(fmt) ? "yyyy-MM-dd HH:mm:ss" : fmt;
        Date fmtDt = (null == dt ? new Date() : dt);
        SimpleDateFormat sdf = new SimpleDateFormat(usedFmt);
        return sdf.format(fmtDt);
    }

    /**
     * 获取昨天日期
     *
     * @return
     */
    public static Date getYesterdayDate() {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     * 获取昨天日期
     *
     * @return
     */
    public static String getYesterday() {
        Date date = getYesterdayDate(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        String yesterday = formatter.format(date);
        return yesterday;
    }

    /**
     * 获取之前或之后天数日期
     *
     * @return
     */
    public static Date getBeforeOrAfterDate(int days) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, days);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取今天当日开始秒数 00:00:00
     *
     * @return
     */
    public static String getTodayStartSecString() {
        Date today = getToday();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        String todayString = formatter.format(today);
        return todayString +" 00:00:00";
    }


    /**
     * 获取今天当日开始秒数 00:00:00
     *
     * @return
     */
    public static String getStartSecStringByDate(Date  date) {

        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        String dateString = formatter.format(date);
        return dateString;
        // return dateString +START_SECOND;
    }


    /**
     * 获取日期最后秒数 23:59:59
     *
     * @return
     */
    public static String getLastSecStringByData(Date  date) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        String dateString = formatter.format(date);
        return dateString +ENd_SECOND;
    }


    /**
     * 获取今天当日最后秒数 23:59:59
     *
     * @return
     */
    public static String getTodayLastSecString() {
        Date today = getToday();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        String todayString = formatter.format(today);
        return todayString +" 23:59:59";
    }


    /**
     * 获取今天日期
     *
     * @return
     */
    public static String getTodayString() {
        Date today = getToday();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        String todayString = formatter.format(today);
        return todayString;
    }

    /**
     * 获取今天日期
     *
     * @return
     */
    public static Date getTodayDate() {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        return date;
    }

    /**
     * 获取今天日期(24小时)
     *
     * @return
     */
    public static String getTodayString_24() {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_TIME_24);
        return formatter.format(new Date());
    }

    /**
     * 获取明天日期
     *
     * @return
     */
    public static String getTomorrow() {
        Date today = getToday();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        String tomorrow = formatter.format(calculateTime(today, FIELD_DAY, 1));
        return tomorrow;
    }

    /**
     * 获取明天日期
     *
     * @return
     */
    public static String getDayAfterDay() {
        Date today = getToday();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        String dayAfterDay = formatter.format(calculateTime(today, FIELD_DAY, 2));
        return dayAfterDay;
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pTime 修要判断的时间<br>
     * @return dayForWeek 判断结果<br>
     * @Exception 发生异常<br>
     */
    public static int dayForWeek(String pTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(pTime));
        } catch (ParseException e) {
            logger.error("ParseException in DateUtil.parse ...", e);
        }
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获取本周的第一天和最后一天
     *
     * @return
     */
    public static Map<String, LocalDate> getFirstAndEndDayOfWeek() {
        Map<String, LocalDate> result = new HashMap<>();
        TemporalAdjuster FIRST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate ->
                localDate.minusDays((long)(localDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue())));
        result.put("firstDay", LocalDate.now().with(FIRST_OF_WEEK));
        TemporalAdjuster LAST_OF_WEEK = TemporalAdjusters.ofDateAdjuster(localDate ->
                localDate.plusDays((long)(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue())));
        result.put("lastDay", LocalDate.now().with(LAST_OF_WEEK));
        return result;
    }

    /**
     * 获取本月的第一天和最后一天
     *
     * @return
     */
    public static Map<String, LocalDate> getFirstAndEndDayOfMonth() {
        Map<String, LocalDate> result = new HashMap<>();
        LocalDate today = LocalDate.now();
        //本月的第一天
        LocalDate firstDay = LocalDate.of(today.getYear(), today.getMonth(), 1);
        //本月的最后一天
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
        result.put("firstDay", firstDay);
        result.put("lastDay", lastDay);
        return result;
    }

    /**
     * 获取本年的第一天和最后一天
     *
     * @return
     */
    public static Map<String, LocalDate> getFirstAndEndDayOfYear() {
        Map<String, LocalDate> result = new HashMap<>();
        LocalDate today = LocalDate.now();
        //本年的第一天
        LocalDate firstDay = LocalDate.of(today.getYear(), 1, 1);
        //本年的最后一天
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfYear());
        result.put("firstDay", firstDay);
        result.put("lastDay", lastDay);
        return result;
    }


    /**
     * 根据生日获取年龄
     *
     * @param birthday 生日（精确到日，例如：1992-10-10）
     * @return
     */
    public static long getAge(String birthday) {
        LocalDate today = LocalDate.now();
        String format = FORMAT_DATE;
        if (StringUtils.isNotEmpty(birthday)) {
            if (birthday.contains("/")) {
                format = FORMAT_DATE_DAY;
            }
        }
        LocalDate playerDate = LocalDate.from(DateTimeFormatter.ofPattern(format).parse(birthday));
        long age = ChronoUnit.YEARS.between(playerDate, today);
        return age;
    }

    /**
     * 获取当年年份
     */
    public static int getCurrentYear() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取当月
     */
    public static int getCurrentMonth() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH);
        return month + 1;
    }

    /**
     * 获取比较月份
     *
     * @param maxMonth
     * @return
     */
    public static List<String> getCompareMonth(int maxMonth) {
        List<String> monthList = new ArrayList<>();
        for (int i = 1; i <= maxMonth; i++) {
            monthList.add(i < 10 ? "0" + i : i + "");
        }
        return monthList;
    }

    /**
     * 取得指定月份的最后一天
     */
    public static String getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return format(calendar.getTime(), "yyyy-MM-dd");
    }

    /**
     * 取得一年的第几周
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        int week_of_year = c.get(Calendar.WEEK_OF_YEAR);
        return week_of_year;
    }

    public static String fomaToDatas(String data) {
        DateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        try {
            Date parse = fmt.parse(data);
            DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd");
            return fmt2.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取指定日期是一周的第几天
     * 星期一 ~ 星期日  即  1 ~  7
     *
     * @param date
     * @return
     */
    public static int getDaysOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int days = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (days == 0)
            return 7;
        return days;
    }

    /**
     * 获取两个日期间隔的所有日期
     * @param start 格式必须为'2018-01-25'
     * @param end 格式必须为'2018-01-25'
     * @return
     */
    public static List<String> getBetweenDate(String start, String end){
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            list.add(start);
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1))
                .limit(distance + 1)
                .forEach(f -> list.add(f.toString()));
        return list;
    }
    /**
     * 在当前的日期上添加多少天数
     */
    public static Date addDay(Date start, Integer num){
        if(start==null){
            start = new Date();
        }
        if(num==null){
            num = 1;
        }
        Calendar ca = Calendar.getInstance();
        ca.setTime(start);
        ca.add(Calendar.DATE, num);
        return ca.getTime();
    }
    /**
     * 获取当前时间的分钟  this.init();
     */
    public static String getHo(Date date){
        DateFormat fmt2 = new SimpleDateFormat(FORMAT_DATE_HHMM);
        return fmt2.format(date);
    }

    /**

     * 日期格式转换 yyyy-MM-dd HH:mm:ss TO yyyy-MM-dd'T'HH:mm:ss.SSSXXX (yyyy-MM-dd'T'HH:mm:ss.SSSZ)

     * 2020-04-09 23:00:00 TO 2020-04-09T23:00:00.000+08:00

     * @throws ParseException

     */

    public static String dealDateFormatReverse(String oldDateStr) throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date1 = df2.parse(oldDateStr);

        return df.format(date1);

    }

}

package com.benson.esignin.common.utils;


import com.benson.esignin.common.cons.CommonCons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Date 工具类，提供常用的时间类型操作方法
 *
 * @since 2016年05月12日 18:37
 * @author Benson Xu
 * @version 1.0
 */
public class DateUtil {

	/**
	 * 获取当前日期时间
	 * 时间格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getCurrentDateTime() {

		return getCurrentDateTime(CommonCons.D_FMT_NORMAL);
	}

	/**
	 * 根据时间格式获取当前日期时间
	 * @param fmt 时间格式，例如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static Date getCurrentDateTime(String fmt) {

		return  dateStrToDate(fmt, getCurrentDateTimeStr(fmt));
	}

	public static String getCurrentDateTimeStr(String fmt) {

		String temp = new SimpleDateFormat(fmt).format(new Date());

		return temp;
	}

	/**
	 * 将时间字符串转换为指定格式的日期时间类型
	 * @param fmt 格式
	 * @param date 时间字符串
	 * @return 日期时间
	 */
	public static Date dateStrToDate(String fmt, String date) {
		Date temp = null;
		try {
			temp = new SimpleDateFormat(fmt).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 字符串转换日期格式
	 * @param dateString 字符串日期格式（yyyy-MM-dd）
	 * @return 日期对象
	 */
	public static Date converToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat(CommonCons.D_FMT_DATE);
	    Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
	    return date;
	}

	/**
	 * 字符串转换日期格式
	 *
	 * @param dateString 字符串日期格式（yyyy-MM-dd HH:mm:ss）
	 * @return 日期对象
	 */
	public static Date converToDatetime(String dateString) {
		if (CommonUtil.isNull(dateString)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(CommonCons.D_FMT_NORMAL);
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}


	/**
	 * 将日期类型转化字符串类型
	 * @param date 目标日期
	 * @return 字符串型日期
	 */
	public static String converToString(Date date) {
		return converToString(date, CommonCons.D_FMT_DATE);
	}
	/**
	 * 将日期类型转化字符串类型
	 * @param date 目标日期
	 * @param format 时间格式，默认“yyyy-MM-dd”
	 * @return 字符串型日期
	 */
	public static String converToString(Date date, String format) {
		if (CommonUtil.isNull(date)) {
			return null;
		}
		if (StringUtil.isNullString(format)) {
			format = CommonCons.D_FMT_DATE;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 获取本月月份
	 * @return 月份整数（1~12）
	 */
	public static Integer getMonthOfThisYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 获得指定日期的当年的第一日的日期
	 * @param date 指定日期
	 * @return Date对象
	 */
	public static Date getTimeOfYearBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, 0);
		return getMonthBegin(cal.getTime());
	}
	
	/**
	 * 获得指定日期的当年的最后一日的日期
	 * @param date 指定日期
	 * @return Date对象
	 */
	public static Date getTimeOfYearEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, 11);
		return getMonthEnd(cal.getTime());
	}
	
	/**
	 * 获取今年指定月份的日期
	 * @param month 指定月份
	 * @return
	 */
	public static Date getTimeByMonth(Integer month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MONTH, month - 1);
		return cal.getTime();
	}

	/**
	 * 获取指定日期的月初的日期
	 * @param date 指定日期
	 * @return Date对象
	 */
	public static Date getMonthBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定日期的月尾的日期
	 * @param date 指定日期
	 * @return Date对象
	 */
	public static Date getMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - 1);
		return cal.getTime();
	}
	
	/**
	 * 获取指定日期的月尾的日历
	 * @param date 指定日期
	 * @return Calendar对象
	 */
	public static Calendar getCalMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - 1);
		return cal;
	}
	
	/**
	 * 获取指定日期所在月份的天数（即月份的后一天）
	 * @param date 指定日期
	 * @return 天数
	 */
	public static int getLastDayOfMonth(Date date) {
		Calendar cal = getCalMonthEnd(date);
		int day = cal.get(Calendar.DATE);
		return day;
	}
	
	/**
	 * 获取指定日期这个礼拜的周一的日期
	 * @param date 指定日期 
	 * @return Date对象
	 */
	public static Date getWeekBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date mm=nDaysAgo(cal.get(Calendar.DAY_OF_WEEK)-2,date);
		return getDayBegin(mm);
	}

	/**
	 * 获取指定日期这个礼拜的周日的日期
	 * @param date 指定日期
	 * @return Date对象
	 */
	public static Date getWeekEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

//		Date mm=nDaysAfter(cal.get(8-Calendar.DAY_OF_WEEK),date);   
		Date mm=nDaysAfter(cal.get(Calendar.DAY_OF_WEEK)+4,date);
		return getDayEnd(mm);
	}
	
	/**
	 * 获取两个日期之间相差的月份数
	 * @param dateBegin 开始日期
	 * @param dateEnd   结束日期
	 * @return 两个日期之间的月份差
	 */
	public static int getDifferenceMonth(Date dateBegin, Date dateEnd){
		SimpleDateFormat yearDateFormat = new SimpleDateFormat("y");
		SimpleDateFormat monthDateFormat = new SimpleDateFormat("M");
		int yearBegin = Integer.parseInt(yearDateFormat.format(dateBegin));
		int yearEnd = Integer.parseInt(yearDateFormat.format(dateEnd));
		int monthBegin = Integer.parseInt(monthDateFormat.format(dateBegin));
		int monthEnd = Integer.parseInt(monthDateFormat.format(dateEnd));
		int differenceYear = yearEnd - yearBegin;
		
		return Math.abs(monthEnd + 12 * differenceYear - monthBegin);
	}

	/**
	 * 获取两个日期时间相差的秒数
	 * @param startTime 开始时间
	 * @param endTime 结束时间
     * @return
     */
	public static int getDifferenceSeconds(Date startTime, Date endTime) {
		// 如果其中一个为空,则返回-1
		if (null == startTime || null==endTime)
			return -1;

		long diffTime = (endTime.getTime() - startTime.getTime())/1000; //单位为秒

		int seconds = diffTime > 0 ? (int)diffTime : (int)Math.abs(diffTime);

		return seconds;
	}
	
	/**
	 * 获取指定日期指定延后天数的日期
	 * @param n	       延后指定天数
	 * @param date 指定日期 
	 * @return
	 */
	public static Date nDaysAfter(int n,Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+n);
		return cal.getTime();
	}

	/**
	 * 获取指定日期中开始时间
	 * @param date 指定日期 
	 * @return
	 */
	public static Date getDayBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定日期中结束时间
	 * @param date 指定日期
	 * @return
	 */
	public static Date getDayEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)+1, 0, 0, 0);
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - 1);
		return cal.getTime();
	}
   
	/**
	 * 获取指定日期推前指定月数的日期
	 * @param n    推前指定月数
	 * @param date 指定日期
	 * @return
	 */
	public static Date nMonthsAgo(Integer n,Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - n);
		return cal.getTime();
	}
   
	/**
	 * 获取指定日期延后指定月数的日期
	 * @param n    延后指定月数
	 * @param date 指定日期
	 * @return
	 */
	public static Date nMonthsAfter(Integer n,Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + n);
		return cal.getTime();
	}
	
	/**
	 * 获取指定日期推前指定天数的日期
	 * @param n	        推前指定天数
	 * @param date 指定日期 
	 * @return
	 */
	public static Date nDaysAgo(Integer n,Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) -n);
		return cal.getTime();
	}
	
	/**
	 * 获取当前日期字符串
	 * 默认格式：yyyyMMdd
	 * @param format 日期格式
	 * @return 日期字符串
	 */
	public static String getCurrDate(String format) {
		if (StringUtil.isNullString(format)) {
			format = "yyyyMMdd";
		}
		String fmtDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		fmtDate = sdf.format(new Date()); 
		return fmtDate;
	}
	
	/**
	 * 以服务器上年本月到今年本月，获得最近一年的月份集合
	 * 注意！这里是ArrayList集合，月份从最低的开始存起。
	 * 格式为yyyyMM
	 * @return
	 */
	public static List<String> getOneYearMonthList() {
		Calendar ca = Calendar.getInstance(); 
		String billingYearAndMonth = null;
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH)+1;
		
		List<String> queryMonths = new ArrayList<String>();
		
		for(int m=month; m<=12; m++) {
			
			if(m < 10) {
				billingYearAndMonth = (year-1) + "0" + m;
			} else {
				billingYearAndMonth = (year-1) + "" + m;
			}
			queryMonths.add(billingYearAndMonth);
		}
		
		for(int m=1; m<=month; m++) {
			if(m < 10) {
				billingYearAndMonth = year + "0" + m;
			} else {
				billingYearAndMonth = year + "" + m;
			}
			queryMonths.add(billingYearAndMonth);
		}
		
		
		return queryMonths;
	}
	
	
	/**
	 * 以服务器上年本月到今年本月，获得最近一年的月份集合
	 * 注意！这里是ArrayList集合，月份从最低的开始存起。
	 * 格式为yyyy-MM
	 * @return
	 */
	public static List<String> getOneYearMonthList2() {
		Calendar ca = Calendar.getInstance(); 
		String billingYearAndMonth = null;
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH)+1;
		
		List<String> queryMonths = new ArrayList<String>();
		
		for(int m=month; m<=12; m++) {
			
			if(m < 10) {
				billingYearAndMonth = (year-1) + "-0" + m;
			} else {
				billingYearAndMonth = (year-1) + "-" + m;
			}
			queryMonths.add(billingYearAndMonth);
		}
		
		for(int m=1; m<=month; m++) {
			if(m < 10) {
				billingYearAndMonth = year + "-0" + m;
			} else {
				billingYearAndMonth = year + "-" + m;
			}
			queryMonths.add(billingYearAndMonth);
		}
		
		
		return queryMonths;
	}
	
	/**
	 * 去掉日期中含有“regex”格式的字符
	 * @param date
	 * @param regex
	 */
	public static String clearDataFmt(String date, String regex) {
		
		return date.replaceAll(regex, "");
	}
	
	public static String addDataRegex(String date, String regex) {
		
		return date.substring(0, 4) + regex + date.substring(4, 6) + regex + date.substring(6);
	}
	
	/**
	 * 如theDate = 2015-01-01, amount = 1  --> return 2015-01-02<br>
	 * 如theDate = 2015-02-04, amount = -2 --> return 2015-02-02
	 * 
	 * @param theDate 给特定的时间（格式为yyyy-MM-dd）
	 * @param amount 需要增减的值
	 * @return
	 */
	public static String getDateCalc(String theDate, int amount) {
		String d = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		try {
			Date date = sdf.parse(theDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, amount);
	        d = sdf.format(c.getTime()); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        return d;
	}
	
	/**
	 * 获取指定日期以后几天的日期
	 * @param startDate 指定日期yyyy-MM-dd
	 * @param amount 以后多少天
	 * @return
	 */
	public static List<String> getFutureDay(String startDate, int amount) {
		
		List<String> result = new ArrayList<String>();
		
		for(int x=1; x<6; x++) {
			String date = getDateCalc(startDate, x);
			String[] datePart = date.split("-");
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.parseInt(datePart[0]), Integer.parseInt(datePart[1]), Integer.parseInt(datePart[2]));
		
			result.add(date);
		}
		
		return result;
	}
	
	/**
	 * 输入日期，获得日期对应的周几
	 * @param date yyyy-MM-dd
	 * @return
	 */
	public static String getWeekDayChn(String date) {
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			c.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String weekDay = "";

		if (Calendar.MONDAY == c.get(Calendar.DAY_OF_WEEK)) {
			weekDay = "星期一";
		}
		if (Calendar.TUESDAY == c.get(Calendar.DAY_OF_WEEK)) {
			weekDay = "星期二";
		}
		if (Calendar.WEDNESDAY == c.get(Calendar.DAY_OF_WEEK)) {
			weekDay = "星期三";
		}
		if (Calendar.THURSDAY == c.get(Calendar.DAY_OF_WEEK)) {
			weekDay = "星期四";
		}
		if (Calendar.FRIDAY == c.get(Calendar.DAY_OF_WEEK)) {
			weekDay = "星期五";
		}
		if (Calendar.SATURDAY == c.get(Calendar.DAY_OF_WEEK)) {
			weekDay = "星期六";
		}
		if (Calendar.SUNDAY == c.get(Calendar.DAY_OF_WEEK)) {
			weekDay = "星期日";
		}

		return weekDay;
	}
	
	/**
	 * 输入日期，获得日期对应的月份中文
	 * @param date yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String getMonthChn(String date) {
		String month = "";
		switch (Integer.parseInt(date.split("-")[1])) {
		case 1:
			month = "一月";
			break;
		case 2:
			month = "二月";
			break;
		case 3:
			month = "三月";
			break;
		case 4:
			month = "四月";
			break;
		case 5:
			month = "五月";
			break;
		case 6:
			month = "六月";
			break;
		case 7:
			month = "七月";
			break;
		case 8:
			month = "八月";
			break;
		case 9:
			month = "九月";
			break;
		case 10:
			month = "十月";
			break;
		case 11:
			month = "十一月";
			break;
		case 12:
			month = "十二月";
			break;
		}
		return month;
	}
	
	/**
	 * 输入日期，获得日期对应的日期dd(-->d)
	 * @param date yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String getDateNum(String date) {

		return "" + Integer.parseInt(date.split("-")[2]);
	}
	
	/**
	 * 时间对比，return time1 - time2 ==> -1, 0, 1
	 * @param time1 hh:mm:ss
	 * @param time2 hh:mm:ss
	 * @return
	 */
	public static int compareTime(String time1, String time2) {
		DateFormat df = new SimpleDateFormat("hh:mm:ss");
		try {
			Date d1 = df.parse(time1);
			Date d2 = df.parse(time2);
			if (d1.getTime() > d2.getTime()) {

				return 1;
			} else if (d1.getTime() < d2.getTime()) {

				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;

	}
	
	/**
	 * 获取日期所在月份的所有日期集合
	 * 	返回的日期格式：yyyy-MM-dd
	 * @param yearMonth 年月（格式：yyyy-MM）
	 * @return
	 */
	public static List<String> getAllDateOfMonth(String yearMonth) {
		if (StringUtil.isNullString(yearMonth) || yearMonth.length()>7) {
			System.out.println(String.format("当前年月【%s】不符合预期格式：yyyy-MM", yearMonth));
			return null;
		}
		
		List<String> dates = new ArrayList<String>();
		// 声明为日期格式
		String dateStr = yearMonth + "-01";
		Date d = DateUtil.converToDate(dateStr);
		Date date = DateUtil.getMonthEnd(d);
		int days= getLastDayOfMonth(date);
		for (int i = 1; i <= days; i++) {
			dateStr = i < 10 ? String.format("%s-0%d", yearMonth, i) : String.format("%s-%d", yearMonth, i);
			dates.add(dateStr);
		}
		
		return dates;
	}
	
	
	/**
	 * 获取指定日期后续7天的日期，不包括指定日期本身
	 * @param date 指定日期
	 * @return 后续7天的日期集合
	 */
	public static List<Date> getFollow7Day(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 日期集合
		List<Date> dates = new ArrayList<Date>();
		cal.add(Calendar.DATE, 1);	//1天后的日期
		dates.add(cal.getTime());
		cal.add(Calendar.DATE, 1);	//2天后的日期
		dates.add(cal.getTime());
		cal.add(Calendar.DATE, 1);	//3天后的日期
		dates.add(cal.getTime());
		cal.add(Calendar.DATE, 1);	//4天后的日期
		dates.add(cal.getTime());
		cal.add(Calendar.DATE, 1);	//5天后的日期
		dates.add(cal.getTime());
		cal.add(Calendar.DATE, 1);	//6天后的日期
		dates.add(cal.getTime());
		cal.add(Calendar.DATE, 1);	//7天后的日期
		dates.add(cal.getTime());
		
		return dates;
	}
	
	/**
	 * 比较两个日期对象
	 * @param d1
	 * @param d2
	 * @return 0是d1和d2相等；1是d1在d2之后；-1是d1在d2之前
	 */
	public static int compareDate(Date d1, Date d2) {
		if (CommonUtil.isNull(d1) || CommonUtil.isNull(d2)) {
			return -2; //有日期为空
		}
		
		if (d1.getTime() > d2.getTime()) {	//date1在date2前
            return 1;
        } else if (d1.getTime() < d2.getTime()) { //date1在date2后
            return -1;
        } else {
            return 0; //两个日期相同
        }
	}
	/**
	 * 比较两个日期对象
	 * @param date1
	 * @param date2
	 * @return 0是d1和d2相等；1是d1在d2之后；-1是d1在d2之前
	 */
	public static int compareDate(String date1, String date2) {
	DateFormat df = new SimpleDateFormat(CommonCons.D_FMT_DATE);
    try {
        Date d1 = df.parse(date1);
        Date d2 = df.parse(date2);
        return compareDate(d1, d2);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}
	
	/*public static void main(String[] args) {
		System.out.println(clearDataFmt("2014-01-02", "-"));
		System.out.println(addDataRegex("20140102", "/"));
	}*/


	/**
	 * 将日期格式转换为yyyy-MM-dd格式的字符串
	 *
	 * @param date
	 * @return
	 */
	public static String formatYMD(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(CommonCons.D_FMT_DATE);
		return sdf.format(date);
	}

	/**
	 * 将日期格式转换为yyyyMMdd格式的字符串
	 *
	 * @param date
	 * @return
	 */
	public static String formatYMD1(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(CommonCons.D_FMT_DATE_SEQ);
		return sdf.format(date);
	}

	/**
	 * 将日期格式转换为yyyy-MM-dd HH:mm:ss格式的字符串
	 *
	 * @param date
	 * @return
	 */
	public static String formatYMDHMS(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(CommonCons.D_FMT_NORMAL);
		return sdf.format(date);
	}

	/**
	 * 按 yyyy-MM-dd 的格式将字符串转换成日期
	 *
	 * @param str 需要转换的日期字符串
	 */
	public static Date parseYMD(String str) {
		return parse(str, CommonCons.D_FMT_DATE);
	}

	/**
	 * 按 yyyyMMdd 的格式将字符串转换成日期
	 *
	 * @param str 需要转换的日期字符串
	 */
	public static Date parseYMD1(String str) {
		return parse(str, CommonCons.D_FMT_DATE_SEQ);
	}

	/**
	 * 按指定格式将日期字符串转成日期ַ
	 *
	 * @param str    待转换的日期格式字符串
	 * @param format 格式
	 */
	public static Date parse(String str, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 按 yyyy-MM-dd HH:mm:ss 的格式将字符串转换成日期
	 *
	 * @param str 需要转换的日期字符串
	 */
	public static Date parseYMDHMS(String str) {
		return parse(str, CommonCons.D_FMT_NORMAL);
	}


	/**
	 * 获取给定时间的几分钟前的时间
	 * 如果date不指定，则默认当前时间
	 * @param date 指定时间
	 * @param minute 前几分钟
	 * @return
	 */
	public static String getTimeByBeforeMinute(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();
		if(null != date) calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -minute);

		return new SimpleDateFormat(CommonCons.D_FMT_NORMAL).format(calendar.getTime());
	}

	/**
	 * 获取给定时间的几分钟后的时间
	 * 如果date不指定，则默认当前时间
	 * @param date 指定时间
	 * @param minute 前几分钟
	 * @return
	 */
	public static String getTimeByAfterMinute(Date date, int minute) {

		return new SimpleDateFormat(CommonCons.D_FMT_NORMAL).format(addMinuteToDate(date, minute));
	}

	/**
	 * 获取给定时间的几分钟后的时间
	 * 如果date不指定，则默认当前时间
	 * @param date 指定时间
	 * @param minutes 前几分钟
	 * @return
	 */
	public static Date addMinuteToDate(Date date, int minutes) {

		Calendar calendar = Calendar.getInstance();
		if(null != date) calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);

		return calendar.getTime();
	}

	/**
	 * 给指定时间添加小时数，24小时制
	 * @param date 目标日期
	 * @param hours 1~23 小时数
	 * @return
	 */
	public static Date addHourToDate(Date date, int hours) {

		Calendar calendar = Calendar.getInstance();
		if(null != date) calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hours);

		return calendar.getTime();
	}

}

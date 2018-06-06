package com.zp.core.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static final String defaultPattern = "yyyy-MM-dd HH:mm:ss";
	public static final int TRUNCTO_YEAR = 0;
	public static final int TRUNCTO_MONTH = 1;
	public static final int TRUNCTO_DAY = 2;

	public static String current(String pattern) {
		return formatDate(current(), pattern);
	}

	public static Date current() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	public static Date currentDate() {
		java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
		return date;
	}

	public static Timestamp currentTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp addDay(Timestamp time, int days) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time.getTime());
		c.add(6, days);
		return new Timestamp(c.getTimeInMillis());
	}
	
	public static Timestamp addDay(Date time, int days) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time.getTime());
		c.add(6, days);
		return new Timestamp(c.getTimeInMillis());
	}
	public static Timestamp trunc(Timestamp time, int truncTo) {

		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time.getTime());
		int year = 0, month = 0, date = 1, hour = 0, minute = 0, second = 0;
		if (truncTo >= TRUNCTO_YEAR) {
			year = c.get(Calendar.YEAR);
		}
		if (truncTo >= TRUNCTO_MONTH) {
			month = c.get(Calendar.MONTH);
		}
		if (truncTo >= TRUNCTO_DAY) {
			date = c.get(Calendar.DAY_OF_MONTH);
		}
		c.set(year, month, date, hour, minute, second);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static void main(String[] args) {
		String day = DateUtils.current("dd");
		System.out.println(day);
	}

	public static java.sql.Date addDay(java.sql.Date date, int days) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.add(6, days);
		return new java.sql.Date(c.getTimeInMillis());
	}

	public static Date add(Date date, int filed, int amount) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		c.add(filed, amount);
		return new Date(c.getTimeInMillis());
	}

	public static Calendar toCalander(Timestamp timestamp) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(timestamp.getTime());
		return c;
	}
	public static Calendar toCalander(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date.getTime());
		return c;
	}
	/**
	 * format date use default pattern: yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return 
	 */
	public static String formatDate(Date date) {
		return formatDate(date, defaultPattern);
	}

	public static String formatDate(Date date, String pattern) {
		if (date == null)
			return null;
		if (pattern == null) {
			throw new IllegalArgumentException("pattern is null");
		} else {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			return formatter.format(date);
		}
	}

	public static Date parse(String dateStr, Class resultClass) throws ParseException {
		return parse(dateStr, defaultPattern, resultClass);
	}

	public static Date parseAsDate(String dateStr) {
		return parse(dateStr, defaultPattern, java.sql.Date.class);
	}
	public static Date parseAsDate(String dateStr,String pattern) {
		return parse(dateStr, pattern, java.sql.Date.class);
	}
	public static Date parseAsTimestamp(String dateStr) {
		return parse(dateStr, defaultPattern, Timestamp.class);
	}

	public static Date parse(String dateStr, String pattern, Class resultClass) {
		if (dateStr == null)
			throw new IllegalArgumentException("dateStr is null");
		if (pattern == null) {
			throw new IllegalArgumentException("pattern is null");
		} else {

			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			Date date;
			try {
				date = formatter.parse(dateStr);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
			if (resultClass == null)
				return date;
			else if (resultClass.equals(java.sql.Date.class)) {
				return new java.sql.Date(date.getTime());
			} else if (resultClass.equals(Timestamp.class)) {
				return new Timestamp(date.getTime());
			} else {
				throw new IllegalArgumentException(
					"result class must be java.sql.Date or java.sql.Timestamp");
			}
		}
	}

	public static Date addMonth(int amount) {
		return add(currentTime(), Calendar.MONTH, amount);
	}

	public static Date addMonth(Date date, int amount) {
		return add(date, Calendar.MONTH, amount);
	}
	
	public static Date addYear(Date date, int amount) {
		return add(date, Calendar.YEAR, amount);
	}

	public static int getDay() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
}

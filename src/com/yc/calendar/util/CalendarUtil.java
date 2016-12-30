package com.yc.calendar.util;

import java.util.Calendar;

import android.util.Log;

public class CalendarUtil {
	/**
	 * 每个月的天数 TimeMouth[0]=31代表去年的12月为31天 TimeMouth[13]=31代表明年的1月为31天
	 */
	public static int[] TimeMouth = { 31, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
			31 };
	
	public static void getLineTimeData(int[] timeData) {
		Calendar day = Calendar.getInstance();
		// 获取到的星期范围：1~7 1=星期日 7=星期六，其他类推
		int week = day.get(Calendar.DAY_OF_WEEK);
		int now = day.get(Calendar.DAY_OF_MONTH);

		timeData[0] = now - week + 1;
		for (int i = 1; i < 7; i++) {
			timeData[i] = timeData[i - 1] + 1;
		}
	}
	
	/**
	 * 初始化timeData里数据，存储当月日期列表
	 * 
	 * @param timeData
	 */
	public static void getTimeData(int []timeData) {

		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);

		if (IsLeapYear(year)) {
			TimeMouth[2] = 29;
		} else {
			TimeMouth[2] = 28;
		}
		// 设为当前月的1号
		calendar.set(Calendar.DATE, 1);
		
		int NowMonth = calendar.get(Calendar.MONTH) + 1;
		// 获取到的星期范围：1~7 1=星期日 7=星期六，其他类推
		int week = calendar.get(Calendar.DAY_OF_WEEK);

		Log.i("Tag", NowMonth + "-" + " " + week);
		// 上个月天数
		int LastMonthDays = TimeMouth[NowMonth - 1];
		// 当前这个月天数
		int NowMonthDays = TimeMouth[NowMonth];

		switch (week) {
		case 1:
			timeData[0] = 1;
			for (int i = 1; i < 42; i++) {
				timeData[i] = timeData[i - 1] + 1;
				if (timeData[i] > NowMonthDays) {
					timeData[i] = 1;
				}
			}
			break;
		case 2:
			timeData[0] = LastMonthDays;
			timeData[1] = 1;
			for (int i = 2; i < 42; i++) {
				timeData[i] = timeData[i - 1] + 1;
				if (timeData[i] > NowMonthDays) {
					timeData[i] = 1;
				}
			}
			break;
		case 3:
			timeData[0] = LastMonthDays - 1;
			timeData[1] = LastMonthDays;

			timeData[2] = 1;
			for (int i = 3; i < 42; i++) {
				timeData[i] = timeData[i - 1] + 1;
				if (timeData[i] > NowMonthDays) {
					timeData[i] = 1;
				}
			}
			break;
		case 4:
			timeData[0] = LastMonthDays - 2;
			timeData[1] = LastMonthDays - 1;
			timeData[2] = LastMonthDays;

			timeData[3] = 1;
			for (int i = 4; i < 42; i++) {
				timeData[i] = timeData[i - 1] + 1;
				if (timeData[i] > NowMonthDays) {
					timeData[i] = 1;
				}
			}
			break;
		case 5:
			timeData[0] = LastMonthDays - 3;
			timeData[1] = LastMonthDays - 2;
			timeData[2] = LastMonthDays - 1;
			timeData[3] = LastMonthDays;

			timeData[4] = 1;
			for (int i = 5; i < 42; i++) {
				timeData[i] = timeData[i - 1] + 1;
				if (timeData[i] > NowMonthDays) {
					timeData[i] = 1;
				}
			}

			break;
		case 6:
			timeData[0] = LastMonthDays - 4;
			timeData[1] = LastMonthDays - 3;
			timeData[2] = LastMonthDays - 2;
			timeData[3] = LastMonthDays - 1;
			timeData[4] = LastMonthDays;
			timeData[5] = 1;
			for (int i = 6; i < 42; i++) {
				timeData[i] = timeData[i - 1] + 1;
				if (timeData[i] > NowMonthDays) {
					timeData[i] = 1;
				}
			}
			break;
		case 7:
			timeData[0] = LastMonthDays - 5;
			timeData[1] = LastMonthDays - 4;
			timeData[2] = LastMonthDays - 3;
			timeData[3] = LastMonthDays - 2;
			timeData[4] = LastMonthDays - 1;
			timeData[5] = LastMonthDays;
			timeData[6] = 1;

			for (int i = 7; i < 42; i++) {
				timeData[i] = timeData[i - 1] + 1;
				if (timeData[i] > NowMonthDays) {
					timeData[i] = 1;
				}
			}
			break;
		}
	}
	/**
	 * 获取现在的年月 
	 * 格式：X年X月
	 * @return
	 */
	public static String getNowYearAndMonth() {
		Calendar calendar = Calendar.getInstance();
		return new String("("+calendar.get(Calendar.YEAR)+"年"+(calendar.get(Calendar.MONTH)+1)+"月"+")");
	}
	/**
	 * 是否是闰年
	 * 
	 * @param year
	 * @return
	 */
	public static boolean IsLeapYear(int year) {
		if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))
			return true;
		return false;
	}
}

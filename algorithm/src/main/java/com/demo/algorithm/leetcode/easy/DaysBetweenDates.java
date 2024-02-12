package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/29
 * @author chenglong
 * description : 日期之间隔几天
 *
 * 请你编写一个程序来计算两个日期之间隔了多少天。
 * 日期以字符串形式给出，格式为 YYYY-MM-DD，如示例所示。
 *
 * 示例 1：
 * 输入：date1 = "2019-06-29", date2 = "2019-06-30"
 * 输出：1
 *
 * 示例 2：
 * 输入：date1 = "2020-01-15", date2 = "2019-12-31"
 * 输出：15
 *
 * 提示：
 * 给定的日期是1971年到2100年之间的有效日期。
 */
public class DaysBetweenDates {

    private final int[] days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int daysBetweenDates(String date1, String date2) {
        for (int i = 1; i < days.length; i++) {
            days[i] += days[i - 1];
        }
        return Math.abs(getDay(date1) - getDay(date2));
    }

    //获取当前时间与1971-01-01之间的间隔天数
    private int getDay(String dates){
        //1，解析获取当前的时间
        int year = Integer.parseInt(dates.substring(0, 4));
        int month = Integer.parseInt(dates.substring(5, 7));
        int day = Integer.parseInt(dates.substring(8));
        int sum = 0;
        //2，转换年
        int spaceYear = year - 1971;
        //计算润年数
        int year1 = (spaceYear + 2) / 4;
        if (year == 2100) {
            year1--;
        }
        sum += (year1 * 366 + (spaceYear - year1) * 365);
        //2，转换月份
        sum += days[month - 1];
        if (year % 4 == 0 && month > 2) {
            //当年为闰年
            sum++;
        }
        //3，转换天
        sum += (day - 1);
        return sum;
    }
}

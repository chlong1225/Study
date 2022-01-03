package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/1/3.
 * description : 一周中的第几天
 *
 *
 */
public class DayOfWeek {

    private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    private int[] dayInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public String dayOfTheWeek(int day, int month, int year) {
        //以1971年1月1日为起点. 当天周五。默认起始位置index =4
        int startYear = 1970;
        int index = 4;
        //1，一年365天后增加的日期
        int addIndexByYear = 365 % 7;
        //2，计算当前年与1971年之间的闰年数量
        int spaceyear = year - 1971;
        //3，统计闰年的数量
        int year2 = 0;
        year2 = ((year - 1) >> 2) - (startYear >> 2);
        year2 -= ((year - 1) / 100 - startYear / 100);
        if (year > 2000) {
            year2++;
        }
        index += (addIndexByYear * spaceyear + year2);
        index %= 7;
        //4，计算每月新增的index
        for (int i = 0; i < 12; i++) {
            dayInMonths[i] = dayInMonths[i] % 7;
        }
        //5，判断当前年是否为闰年
        boolean isLeapYear = false;
        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            isLeapYear = true;
        }
        int count = month - 1;
        for (int i = 0; i < count; i++) {
            index += dayInMonths[i];
            if (i == 1 && isLeapYear) {
                index++;
            }
        }
        index += (day - 1);
        index %= 7;
        return DAYS[index];
    }
}

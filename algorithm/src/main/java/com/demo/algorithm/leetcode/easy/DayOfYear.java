package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2021/12/21.
 * description : 一年中的第几天
 *
 *  给你一个字符串 date，按 YYYY-MM-DD格式表示一个现行公元纪年法日期。请你计算并返回该日期是当年的第几天。
 * 通常情况下，我们认为1月1日是每年的第1天，1月2日是每年的第2天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。 
 *
 * 示例 1：
 * 输入：date = "2019-01-09"
 * 输出：9
 *
 * 示例 2：
 * 输入：date = "2019-02-10"
 * 输出：41
 *
 * 示例 3：
 * 输入：date = "2003-03-01"
 * 输出：60
 *
 * 示例 4：
 * 输入：date = "2004-03-01"
 * 输出：61
 *  
 * 提示：
 * date.length == 10
 * date[4] == date[7] == '-'，其他的 date[i] 都是数字
 * date 表示的范围从 1900 年 1 月 1 日至 2019 年 12 月 31 日
 */
public class DayOfYear {

    private int[] dayNum = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int dayOfYear(String date) {
        int year = (date.charAt(0) - '0') * 1000 + (date.charAt(1) - '0') * 100 + (date.charAt(2) - '0') * 10 + (date.charAt(3) - '0');
        int month = (date.charAt(5) - '0') * 10 + (date.charAt(6) - '0');
        int day = (date.charAt(8) - '0') * 10 + (date.charAt(9) - '0');
        boolean isLeapYear = false;
        if (year % 400 == 0) {
            isLeapYear = true;
        }
        if (year % 4 == 0 && year % 100 != 0) {
            isLeapYear = true;
        }
        int sum = day;
        for (int i = 0; i < month - 1; i++) {
            if (isLeapYear && i == 1) {
                sum += 29;
            } else {
                sum += dayNum[i];
            }
        }
        return sum;
    }
}

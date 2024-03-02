package com.demo.algorithm.leetcode.easy;

/**
 * create on 2024/2/19
 * @author chenglong
 * description : 转变日期格式
 *
 * 给你一个字符串date，它的格式为Day Month Year，其中：
 * Day 是集合 {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"} 中的一个元素。
 * Month 是集合 {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"} 中的一个元素。
 * Year 的范围在 [1900, 2100] 之间。
 * 请你将字符串转变为 YYYY-MM-DD 的格式，其中：
 * YYYY 表示 4 位的年份。
 * MM 表示 2 位的月份。
 * DD 表示 2 位的天数。
 *
 * 示例 1：
 * 输入：date = "20th Oct 2052"
 * 输出："2052-10-20"
 *
 * 示例 2：
 * 输入：date = "6th Jun 1933"
 * 输出："1933-06-06"
 *
 * 示例 3：
 * 输入：date = "26th May 1960"
 * 输出："1960-05-26"
 *
 * 提示：
 * 给定日期保证是合法的，所以不需要处理异常输入。
 */
public class ReformatDate {

    public String reformatDate(String date) {
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] array = date.split(" ");
        int year = Integer.parseInt(array[2]);
        int month = 1;
        for (int i = 0; i < months.length; i++) {
            if (months[i].equals(array[1])) {
                month = i + 1;
            }
        }
        int day = Integer.parseInt(array[0].substring(0, array[0].length() - 2));
        StringBuilder builder = new StringBuilder();
        builder.append(year);
        builder.append("-");
        if (month < 10) {
            builder.append(0);
            builder.append(month);
        } else {
            builder.append(month);
        }
        builder.append("-");
        if (day < 10) {
            builder.append(0);
            builder.append(day);
        } else {
            builder.append(day);
        }
        return builder.toString();
    }
}

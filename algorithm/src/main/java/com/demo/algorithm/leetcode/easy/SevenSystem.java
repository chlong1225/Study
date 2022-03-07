package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/3/7
 * @author chenglong
 * description : 七进制数
 *
 * 给定一个整数num，将其转化为7进制，并以字符串形式输出。
 *
 * 示例 1:
 * 输入: num = 100
 * 输出: "202"
 *
 * 示例 2:
 * 输入: num = -7
 * 输出: "-10"
 *
 * 提示：
 * -10^7<= num <= 10^7
 */
public class SevenSystem {

    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        boolean isPositive = num > 0;
        num = Math.abs(num);
        StringBuilder builder = new StringBuilder();
        while (num >= 7) {
            builder.append(num % 7);
            num /= 7;
        }
        builder.append(num);
        if (!isPositive) {
            builder.append("-");
        }
        return builder.reverse().toString();
    }
}

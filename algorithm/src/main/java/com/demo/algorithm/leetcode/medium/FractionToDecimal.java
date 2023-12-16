package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2023/12/15
 * @author chenglong
 * description : 分数到小数
 *
 * 给定两个整数，分别表示分数的分子numerator和分母denominator，以字符串形式返回小数。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * 如果存在多个答案，只需返回任意一个。
 * 对于所有给定的输入，保证答案字符串的长度小于10^4 。
 *
 * 示例 1：
 * 输入：numerator = 1, denominator = 2
 * 输出："0.5"
 *
 * 示例 2：
 * 输入：numerator = 2, denominator = 1
 * 输出："2"
 *
 * 示例 3：
 * 输入：numerator = 4, denominator = 333
 * 输出："0.(012)"
 *
 * 提示：
 * -2^31 <= numerator, denominator <= 2^31 - 1
 * denominator != 0
 */
public class FractionToDecimal {

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        //1，先判断结果的正负，同时取绝对值。防止-2^31转换后越界。将类型转换为long
        long a = numerator;
        long b = denominator;
        StringBuilder builder = new StringBuilder();
        if (a > 0 && b < 0 || a < 0 && b > 0) {
            builder.append("-");
        }
        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }
        //2，拆分整数部分与小数部分
        if (a % b == 0) {
            return builder.append(a / b).toString();
        }
        long tem = a / b;
        a %= b;
        builder.append(tem);
        builder.append(".");
        //3，获取小数部分
        Map<Long, Integer> marks = new HashMap<>();
        //记录a/b的小数起始位数
        marks.put(a, builder.length());
        while (a != 0) {
            a *= 10;
            tem = a / b;
            builder.append(tem);
            a %= b;
            if (marks.containsKey(a)) {
                //当前重复的部分
                String repeat = builder.substring(marks.get(a));
                builder = builder.delete(marks.get(a), builder.length());
                builder.append("(");
                builder.append(repeat);
                builder.append(")");
                return builder.toString();
            } else {
                marks.put(a, builder.length());
            }
        }
        return builder.toString();
    }
}

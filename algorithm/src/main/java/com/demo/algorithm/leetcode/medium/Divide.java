package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/10/4.
 * description : 两数相除
 *
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 *
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *  
 * 提示：
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 */
public class Divide {

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        int count = 0;
        //1,将被除数与除数转换为正整数
        boolean isNegative = false;
        if (dividend < 0) {
            if (divisor > 0) {
                isNegative = true;
                if (dividend == Integer.MIN_VALUE) {
                    dividend += divisor;
                    count = 1;
                }
                dividend = -dividend;
            } else {
                isNegative = false;
                if (dividend == Integer.MIN_VALUE && divisor == Integer.MIN_VALUE) {
                    return 1;
                }
                if (divisor == Integer.MIN_VALUE) {
                    return 0;
                }
                if (dividend == Integer.MIN_VALUE) {
                    dividend -= divisor;
                    count = 1;
                }
                dividend = -dividend;
                divisor = -divisor;
            }
        } else {
            if (divisor < 0) {
                if (divisor == Integer.MIN_VALUE) {
                    return 0;
                }
                isNegative = true;
                divisor = -divisor;
            }
        }
        //2,通过减法计算
        List<Integer> divistors = new ArrayList<>();
        List<Integer> multiples = new ArrayList<>();
        int multiple = 1;
        int tem = divisor;
        divistors.add(tem);
        multiples.add(multiple);
        while (dividend >= divisor) {
            //减数递增
            if (dividend >= tem) {
                dividend -= tem;
                count += multiple;
                tem = tem + tem;
                multiple = multiple + multiple;
                divistors.add(tem);
                multiples.add(multiple);
            } else {
                //减数递减
                divistors.remove(divistors.size() - 1);
                tem = divistors.get(divistors.size() - 1);
                multiples.remove(multiples.size() - 1);
                multiple = multiples.get(multiples.size() - 1);
            }
        }
        if (isNegative && count > 0) {
            return -count;
        }
        return count;
    }
}

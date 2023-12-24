package com.demo.algorithm.leetcode.lcr;

/**
 * create on 2023/12/22
 * @author chenglong
 * description : LCR 072. x的平方根
 *
 * 给定一个非负整数x，计算并返回x的平方根，即实现int sqrt(int x)函数。
 * 正数的平方根有两个，只输出其中的正数平方根。
 * 如果平方根不是整数，输出只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: x = 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: x = 8
 * 输出: 2
 * 解释: 8 的平方根是 2.82842...，由于小数部分将被舍去，所以返回 2
 *
 * 提示:
 * 0 <= x <= 2^31 - 1
 */
public class MySqrt {

    public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        if (x < 4) {
            return 1;
        }
        int start = 1;
        int end = x / 2;
        while (start < end) {
            int middle = (start + end) / 2;
            long tem = (long) middle * middle;
            if (tem == x) {
                return middle;
            } else if (tem > x) {
                end = middle - 1;
            } else {
                start = middle + 1;
                //检查防止越界
                tem = (long) start * start;
                if (tem > x) {
                    return middle;
                } else if (tem == x) {
                    return start;
                }
            }
        }
        return start;
    }
}

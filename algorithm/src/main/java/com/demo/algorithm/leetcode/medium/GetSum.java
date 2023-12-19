package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/18
 * @author chenglong
 * description : 两整数之和
 *
 * 给你两个整数a和b，不使用运算符+和-，计算并返回两整数之和。
 *
 * 示例 1：
 * 输入：a = 1, b = 2
 * 输出：3
 *
 * 示例 2：
 * 输入：a = 2, b = 3
 * 输出：5
 *
 * 提示：
 * -1000 <= a, b <= 1000
 */
public class GetSum {

    public int getSum(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }
}

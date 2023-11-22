package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/22
 * @author chenglong
 * description : 第N个泰波那契数
 *
 * 泰波那契序列Tn定义如下：
 * T0 = 0, T1 = 1, T2 = 1, 且在n>=0的条件下Tn+3 = Tn + Tn+1 + Tn+2
 * 给你整数n，请返回第n个泰波那契数Tn的值。
 *
 * 示例 1：
 * 输入：n = 4
 * 输出：4
 * 解释：
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 *
 * 示例 2：
 * 输入：n = 25
 * 输出：1389537
 *
 * 提示：
 * 0 <= n <= 37
 * 答案保证是一个 32 位整数，即 answer <= 2^31 - 1。
 */
public class Tribonacci {

    private static final int MAX = 37;
    private static int[] marks;

    static {
        marks = new int[MAX + 1];
        marks[0] = 0;
        marks[1] = 1;
        marks[2] = 1;
        for (int i = 3; i <= MAX; i++) {
            marks[i] = marks[i - 1] + marks[i - 2] + marks[i - 3];
        }
    }

    public int tribonacci(int n) {
        return marks[n];
    }
}

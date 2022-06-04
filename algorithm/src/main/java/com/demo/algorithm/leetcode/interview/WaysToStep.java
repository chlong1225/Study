package com.demo.algorithm.leetcode.interview;

/**
 * Created by chl on 2022/6/4.
 * description : 面试题08.01. 三步问题
 *
 * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
 *
 * 示例1:
 *  输入：n = 3
 *  输出：4
 *  说明: 有四种走法
 *
 * 示例2:
 *  输入：n = 5
 *  输出：13
 *
 * 提示:
 * n范围在[1, 1000000]之间
 */
public class WaysToStep {

    private static final int MOD = 1000000007;

    public int waysToStep(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 4;
        }
        long[] marks = new long[n + 1];
        marks[1] = 1;
        marks[2] = 2;
        marks[3] = 4;
        for (int i = 4; i <= n; i++) {
            marks[i] = marks[i - 1] + marks[i - 2] + marks[i - 3];
            marks[i] %= MOD;
        }
        return (int) marks[n];
    }
}

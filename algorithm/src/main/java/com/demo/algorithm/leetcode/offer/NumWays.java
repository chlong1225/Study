package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/26.
 * description : 剑指Offer10- II. 青蛙跳台阶问题
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：n = 7
 * 输出：21
 *
 * 示例 3：
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 */
public class NumWays {

    private static final int MOD = 1000000007;

    public int numWays(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int[] marks = new int[n + 1];
        marks[0] = 1;
        marks[1] = 1;
        for (int i = 2; i <= n; i++) {
            marks[i] = marks[i - 1] + marks[i - 2];
            marks[i] %= MOD;
        }
        return marks[n];
    }
}

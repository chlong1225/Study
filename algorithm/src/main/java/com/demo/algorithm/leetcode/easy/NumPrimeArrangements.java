package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/6/30.
 * description : 质数排列
 *
 * 请你帮忙给从1到n的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从1开始）上；你需要返回可能的方案总数。
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 * 由于答案可能会很大，所以请你返回答案模 mod10^9 + 7之后的结果即可。
 *
 * 示例 1：
 * 输入：n = 5
 * 输出：12
 * 解释：举个例子，[1,2,5,4,3] 是一个有效的排列，但 [5,2,3,4,1] 不是，因为在第二种情况里质数 5 被错误地放在索引为 1 的位置上。
 *
 * 示例 2：
 * 输入：n = 100
 * 输出：682289015
 *
 * 提示：
 * 1 <= n <= 100
 */
public class NumPrimeArrangements {

    private static final int MOD = 1000000007;
    private static final int[] dates = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97};

    public int numPrimeArrangements(int n) {
        //1，获取质数的数量
        int count = getCount(n);
        //2，计算排列组合
        long sum = 1;
        for (int i = 2; i <= count; i++) {
            sum *= i;
            sum %= MOD;
        }
        for (int i = 2; i <= n - count; i++) {
            sum *= i;
            sum %= MOD;
        }
        return (int) sum;
    }

    private int getCount(int n) {
        int count = 0;
        for (int i = 0; i < dates.length; i++) {
            if (dates[i] <= n) {
                count++;
            }
        }
        return count;
    }

}

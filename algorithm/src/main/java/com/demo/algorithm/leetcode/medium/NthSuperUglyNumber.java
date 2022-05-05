package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2022/5/3.
 * description : 超级丑数
 *
 * 超级丑数是一个正整数，并满足其所有质因数都出现在质数数组primes中。
 * 给你一个整数n和一个整数数组primes，返回第n个超级丑数 。
 * 题目数据保证第n个超级丑数在32-bit带符号整数范围内。
 *
 * 示例 1：
 * 输入：n = 12, primes = [2,7,13,19]
 * 输出：32
 * 解释：给定长度为 4 的质数数组 primes = [2,7,13,19]，前 12 个超级丑数序列为：[1,2,4,7,8,13,14,16,19,26,28,32] 。
 *
 * 示例 2：
 * 输入：n = 1, primes = [2,3,5]
 * 输出：1
 * 解释：1 不含质因数，因此它的所有质因数都在质数数组 primes = [2,3,5] 中。
 *
 * 提示：
 * 1 <= n <= 10^6
 * 1 <= primes.length <= 100
 * 2 <= primes[i] <= 1000
 * 题目数据 保证 primes[i] 是一个质数
 * primes 中的所有值都 互不相同 ，且按 递增顺序 排列
 */
public class NthSuperUglyNumber {

    public int nthSuperUglyNumber(int n, int[] primes) {
        //动态规划
        int[] marks = new int[n + 1];
        marks[1] = 1;
        int length = primes.length;
        int[] indexs = new int[length];
        for (int i = 0; i < length; i++) {
            indexs[i] = 1;
        }
        int[] nums = new int[length];
        for (int i = 2; i <= n; i++) {
            nums[0] = marks[indexs[0]] * primes[0];
            int min = nums[0];
            for (int j = 1; j < length; j++) {
                nums[j] = marks[indexs[j]] * primes[j];
                if (nums[j] < min) {
                    min = nums[j];
                }
            }
            marks[i] = min;
            for (int j = 0; j < length; j++) {
                if (nums[j] == min) {
                    indexs[j]++;
                }
            }
        }
        return marks[n];
    }
}

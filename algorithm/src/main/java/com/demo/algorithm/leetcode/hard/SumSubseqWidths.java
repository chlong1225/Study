package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;

/**
 * create on 2022/11/18
 * @author chenglong
 * description : 子序列宽度之和
 *
 * 一个序列的宽度定义为该序列中最大元素和最小元素的差值。
 * 给你一个整数数组nums，返回nums的所有非空子序列的宽度之和。由于答案可能非常大，请返回对10^9+7取余后的结果。
 * 子序列定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
 *
 * 示例 1：
 * 输入：nums = [2,1,3]
 * 输出：6
 * 解释：子序列为 [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3] 。
 * 相应的宽度是 0, 0, 0, 1, 1, 2, 2 。
 * 宽度之和是 6 。
 *
 * 示例 2：
 * 输入：nums = [2]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class SumSubseqWidths {

    private static final int MOD = 1000000007;

    public int sumSubseqWidths(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        //预处理2的次方
        int[] marks = new int[n];
        marks[0] = 1;
        for (int i = 1; i < n; i++) {
            marks[i] = (marks[i - 1] * 2) % MOD;
        }
        long sum = 0;
        long base = 1;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            //cur作为最小值的次数
            int countMin = marks[n - 1 - i] - 1;
            sum -= (base * countMin * cur % MOD);
            //cur作为最大值的次数
            int countMax = marks[i] - 1;
            sum += (base * countMax * cur % MOD);
            sum %= MOD;
        }
        return (int) (sum%MOD);
    }
}

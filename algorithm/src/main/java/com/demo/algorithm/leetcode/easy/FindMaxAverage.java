package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/30
 * @author chenglong
 * description : 子数组最大平均数I
 *
 * 给你一个由n个元素组成的整数数组nums和一个整数k。
 * 请你找出平均数最大且长度为k的连续子数组，并输出该最大平均数。
 * 任何误差小于10^-5的答案都将被视为正确答案。
 *
 * 示例 1：
 * 输入：nums = [1,12,-5,-6,50,3], k = 4
 * 输出：12.75
 * 解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 *
 * 示例 2：
 * 输入：nums = [5], k = 1
 * 输出：5.00000
 *
 * 提示：
 * n == nums.length
 * 1 <= k <= n <= 10^5
 * -10^4 <= nums[i] <= 10^4
 */
public class FindMaxAverage {

    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        double base = 1.0;
        int sum = 0;
        int add = 0;
        if (k == n) {
            for (int i = 0; i < n; i++) {
                sum += nums[i];
                add += (sum / k);
                sum %= k;
            }
            return add + base * sum / k;
        }
        for (int i = 0; i < k; i++) {
            sum += nums[i];
            add += (sum / k);
            sum %= k;
        }
        int maxAdd = add;
        int maxLast = sum;
        for (int i = k; i < n; i++) {
            sum += nums[i];
            sum -= nums[i - k];
            add += (sum / k);
            sum %= k;
            if (add > maxAdd) {
                maxAdd = add;
                maxLast = sum;
            } else if (add == maxAdd) {
                if (sum > maxLast) {
                    maxLast = sum;
                }
            }
        }
        return maxAdd + base * maxLast / k;
    }
}

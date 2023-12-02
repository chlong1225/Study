package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/11/29
 * @author chenglong
 * description : 乘积最大子数组
 *
 * 给你一个整数数组nums，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个32-位整数。
 * 子数组是数组的连续子序列。
 *
 * 示例 1:
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积6。
 *
 * 示例 2:
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 提示:
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * nums的任何前缀或后缀的乘积都保证是一个32-位整数
 */
public class MaxProduct {

    public int maxProduct(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        //维护以nums[i]结尾的最大最小乘积。
        int[][] marks = new int[n][2];
        marks[0][0] = nums[0];
        marks[0][1] = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == 0) {
                marks[i][0] = 0;
                marks[i][1] = 0;
            } else if (nums[i] > 0) {
                marks[i][0] = Math.max(nums[i], nums[i] * marks[i - 1][0]);
                marks[i][1] = Math.min(nums[i], nums[i] * marks[i - 1][1]);
            } else {
                marks[i][0] = Math.max(nums[i], nums[i] * marks[i - 1][1]);
                marks[i][1] = Math.min(nums[i], nums[i] * marks[i - 1][0]);
            }
            if (marks[i][0] > max) {
                max = marks[i][0];
            }
        }
        return max;
    }
}

package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/11/20
 * @author chenglong
 * description : 最大子数组和
 *
 * 给你一个整数数组nums，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 * 进阶：如果你已经实现复杂度为O(n)的解法，尝试使用更为精妙的分治法求解。
 */
public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] marks = new int[n];
        marks[0] = nums[0];
        int max = marks[0];
        for (int i = 1; i < n; i++) {
            marks[i] = Math.max(marks[i - 1] + nums[i], nums[i]);
            if (marks[i] > max) {
                max = marks[i];
            }
        }
        return max;
    }
}

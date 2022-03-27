package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer42. 连续子数组的最大和
 *
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 *
 * 示例1:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释:连续子数组[4,-1,2,1]的和最大，为6。
 *
 * 提示：
 * 1 <=arr.length <= 10^5
 * -100 <= arr[i] <= 100
 */
public class MaxSubArray {

    public int maxSubArray(int[] nums) {
        /**
         * 分析：对当前每个数字有两个状态，添加或不添加。
         * 对子序列有三种状态：不添加时之前和。添加时与之前和叠加，当前值单独
         */
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int max = nums[0];
        int preMax = nums[0];
        for (int i = 1; i < length; i++) {
            preMax = Math.max(preMax + nums[i], nums[i]);
            max = Math.max(preMax, max);
        }
        return max;
    }
}

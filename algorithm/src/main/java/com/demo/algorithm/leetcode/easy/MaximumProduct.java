package com.demo.algorithm.leetcode.easy;

import java.util.Arrays;

/**
 * create on 2023/11/30
 * @author chenglong
 * description : 三个数的最大乘积
 *
 * 给你一个整型数组nums，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：6
 *
 * 示例 2：
 * 输入：nums = [1,2,3,4]
 * 输出：24
 *
 * 示例 3：
 * 输入：nums = [-1,-2,-3]
 * 输出：-6
 *
 * 提示：
 * 3 <= nums.length <= 10^4
 * -1000 <= nums[i] <= 1000
 */
public class MaximumProduct {

    public int maximumProduct(int[] nums) {
        int n = nums.length;
        if (n == 3) {
            return nums[0] * nums[1] * nums[2];
        }
        Arrays.sort(nums);
        int max = nums[n - 1] * nums[n - 2] * nums[n - 3];
        int tem = nums[0] * nums[1] * nums[n - 1];
        if (tem > max) {
            max = tem;
        }
        return max;
    }
}

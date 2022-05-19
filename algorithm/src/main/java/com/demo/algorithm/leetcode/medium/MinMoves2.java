package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * create on 2022/5/19
 * @author chenglong
 * description : 最少移动次数使数组元素相等II
 *
 * 给你一个长度为n的整数数组nums，返回使所有数组元素相等需要的最少移动数。
 * 在一步操作中，你可以使数组中的一个元素加1或者减1 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：
 * 只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 *
 * 示例 2：
 * 输入：nums = [1,10,2,9]
 * 输出：16
 *
 * 提示：
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MinMoves2 {

    public int minMoves2(int[] nums) {
        //1，排序数据
        Arrays.sort(nums);
        int length = nums.length;
        int target = nums[(length - 1) / 2];
        int sum = 0;
        //2，遍历统计
        for (int i = 0; i < length; i++) {
            sum += Math.abs(nums[i] - target);
        }
        return sum;
    }
}

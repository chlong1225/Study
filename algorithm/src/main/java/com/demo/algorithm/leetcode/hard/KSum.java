package com.demo.algorithm.leetcode.hard;

import java.util.Arrays;

/**
 * create on 2024/3/9
 * @author chenglong
 * description : 找出数组的第K大和
 *
 * 给你一个整数数组nums和一个正整数k。你可以选择数组的任一子序列并且对其全部元素求和。
 * 数组的第k大和定义为：可以获得的第k个最大子序列和（子序列和允许出现重复）
 * 返回数组的第k大和。
 * 子序列是一个可以由其他数组删除某些或不删除元素排生而来的数组，且派生过程不改变剩余元素的顺序。
 * 注意：空子序列的和视作 0 。
 *
 * 示例 1：
 * 输入：nums = [2,4,-2], k = 5
 * 输出：2
 * 解释：所有可能获得的子序列和列出如下，按递减顺序排列：
 * - 6、4、4、2、2、0、0、-2
 * 数组的第 5 大和是 2 。
 *
 * 示例 2：
 * 输入：nums = [1,-2,3,4,-10,12], k = 16
 * 输出：10
 * 解释：数组的第 16 大和是 10 。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 1 <= k <= min(2000, 2^n)
 */
public class KSum {

    public long kSum(int[] nums, int k) {
        //1，对数组进行排序
        Arrays.sort(nums);

        return 0;
    }
}

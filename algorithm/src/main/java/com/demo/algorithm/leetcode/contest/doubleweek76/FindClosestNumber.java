package com.demo.algorithm.leetcode.contest.doubleweek76;

/**
 * Created by chl on 2022/4/19.
 * description : 找到最接近0的数字
 *
 * 给你一个长度为n的整数数组nums，请你返回nums中最 接近0的数字。如果有多个答案，请你返回它们中的最大值。
 *
 * 示例 1：
 * 输入：nums = [-4,-2,1,4,8]
 * 输出：1
 * 解释：
 * -4 到 0 的距离为 |-4| = 4 。
 * -2 到 0 的距离为 |-2| = 2 。
 * 1 到 0 的距离为 |1| = 1 。
 * 4 到 0 的距离为 |4| = 4 。
 * 8 到 0 的距离为 |8| = 8 。
 * 所以，数组中距离 0 最近的数字为 1 。
 *
 * 示例 2：
 * 输入：nums = [2,-1,1]
 * 输出：1
 * 解释：1 和 -1 都是距离 0 最近的数字，所以返回较大值 1 。
 *
 * 提示：
 * 1 <= n <= 1000
 * -10^5 <= nums[i] <= 10^5
 */
public class FindClosestNumber {

    public int findClosestNumber(int[] nums) {
        int length = nums.length;
        int result = nums[0];
        for (int i = 1; i < length; i++) {
            if (Math.abs(nums[i]) < Math.abs(result)) {
                result = nums[i];
            } else if (Math.abs(nums[i]) == Math.abs(result)) {
                if (nums[i] > result) {
                    result = nums[i];
                }
            }
        }
        return result;
    }
}

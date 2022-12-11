package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/12/11.
 * description : 最少操作使数组递增
 *
 * 给你一个整数数组nums（下标从0开始）。每一次操作中，你可以选择数组中一个元素，并将它增加1。
 * 比方说，如果nums=[1,2,3]，你可以选择增加nums[1]得到nums = [1,3,3]。
 * 请你返回使nums严格递增的最少操作次数。
 * 我们称数组nums是严格递增的，当它满足对于所有的0 <= i < nums.length - 1都有nums[i] < nums[i+1]。
 * 一个长度为1的数组是严格递增的一种特殊情况。
 *
 * 示例 1：
 * 输入：nums = [1,1,1]
 * 输出：3
 * 解释：你可以进行如下操作：
 * 1) 增加 nums[2] ，数组变为 [1,1,2] 。
 * 2) 增加 nums[1] ，数组变为 [1,2,2] 。
 * 3) 增加 nums[2] ，数组变为 [1,2,3] 。
 *
 * 示例 2：
 * 输入：nums = [1,5,2,4,1]
 * 输出：14
 *
 * 示例 3：
 * 输入：nums = [8]
 * 输出：0
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * 1 <= nums[i] <= 10^4
 */
public class MinOperations3 {

    public int minOperations(int[] nums) {
        int sum = 0;
        int length = nums.length;
        int target = nums[0] + 1;
        for (int i = 1; i < length; i++) {
            if (nums[i] >= target) {
                target = nums[i] + 1;
            } else {
                sum += (target - nums[i]);
                target++;
            }
        }
        return sum;
    }
}

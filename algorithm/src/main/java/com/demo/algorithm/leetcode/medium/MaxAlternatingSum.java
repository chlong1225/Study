package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/7/11
 * @author chenglong
 * description : 最大子序列交替和
 *
 * 一个下标从0开始的数组的交替和定义为偶数下标处元素之和减去奇数下标处元素之和。
 * 比方说，数组[4,2,5,3]的交替和为(4 + 5) - (2 + 3) = 4。
 * 给你一个数组nums，请你返回nums中任意子序列的最大交替和（子序列的下标重新从0开始编号）。
 * 一个数组的子序列是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，[2,7,4]是[4,2,3,7,2,1,4]的一个子序列（加粗元素），但是[2,4,2]不是。
 *
 * 示例 1：
 * 输入：nums = [4,2,5,3]
 * 输出：7
 * 解释：最优子序列为 [4,2,5] ，交替和为 (4 + 5) - 2 = 7 。
 *
 * 示例 2：
 * 输入：nums = [5,6,7,8]
 * 输出：8
 * 解释：最优子序列为 [8] ，交替和为 8 。
 *
 * 示例 3：
 * 输入：nums = [6,2,1,2,4,5]
 * 输出：10
 * 解释：最优子序列为 [6,1,5] ，交替和为 (6 + 5) - 1 = 10 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 */
public class MaxAlternatingSum {

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long result = nums[0];
        long odd = 0;
        for (int i = 1; i < n; i++) {
            long pre = result - nums[i];
            result = Math.max(result, odd + nums[i]);
            odd = Math.max(odd, pre);
        }
        return result;
    }
}

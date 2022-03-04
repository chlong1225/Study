package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/3/4
 * @author chenglong
 * description : 子数组范围和
 *
 * 给你一个整数数组nums 。nums中，子数组的范围是子数组中最大元素和最小元素的差值。
 * 返回nums中所有子数组范围的和 。
 * 子数组是数组中一个连续非空的元素序列。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [2]，范围 = 2 - 2 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,2]，范围 = 2 - 1 = 1
 * [2,3]，范围 = 3 - 2 = 1
 * [1,2,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
 *
 * 示例 2：
 * 输入：nums = [1,3,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [3]，范围 = 3 - 3 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,3]，范围 = 3 - 1 = 2
 * [3,3]，范围 = 3 - 3 = 0
 * [1,3,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 2 + 0 + 2 = 4
 *
 * 示例 3：
 * 输入：nums = [4,-2,-3,4,1]
 * 输出：59
 * 解释：nums 中所有子数组范围的和是 59
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * -10^9 <= nums[i] <= 10^9
 */
public class SubArraysSum {

    public long subArrayRanges(int[] nums) {
        /**
         * 数据量10^3比较少，可以直接使用双层循环的暴力解法
         */
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        long sum = 0;
        for (int i = 0; i < length - 1; i++) {
            //统计从i～j子集范围的最大最小值
            int min = nums[i];
            int max = nums[i];
            for (int j = i + 1; j < length; j++) {
                if (nums[j] > max) {
                    max = nums[j];
                } else if (nums[j] < min) {
                    min = nums[j];
                }
                sum += (max - min);
            }
        }
        return sum;
    }
}

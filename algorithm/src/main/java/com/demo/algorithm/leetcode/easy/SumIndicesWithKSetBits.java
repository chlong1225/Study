package com.demo.algorithm.leetcode.easy;

import java.util.List;

/**
 * create on 2024/1/25
 * @author chenglong
 * description : 计算K置位下标对应元素的和
 * 给你一个下标从0开始的整数数组nums和一个整数k。
 * 请你用整数形式返回nums中的特定元素之和，这些特定元素满足：其对应下标的二进制表示中恰存在k个置位。
 * 整数的二进制表示中的1就是这个整数的置位。
 * 例如，21的二进制表示为 10101 ，其中有3个置位。
 *
 * 示例 1：
 * 输入：nums = [5,10,1,5,2], k = 1
 * 输出：13
 * 解释：下标的二进制表示是：
 * 0 = 0002
 * 1 = 0012
 * 2 = 0102
 * 3 = 0112
 * 4 = 1002
 * 下标 1、2 和 4 在其二进制表示中都存在 k = 1 个置位。
 * 因此，答案为 nums[1] + nums[2] + nums[4] = 13 。
 *
 * 示例 2：
 * 输入：nums = [4,3,2,1], k = 2
 * 输出：1
 * 解释：下标的二进制表示是：
 * 0 = 002
 * 1 = 012
 * 2 = 102
 * 3 = 112
 * 只有下标 3 的二进制表示中存在 k = 2 个置位。
 * 因此，答案为 nums[3] = 1 。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 10^5
 * 0 <= k <= 10
 */
public class SumIndicesWithKSetBits {

    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (Integer.bitCount(i) == k) {
                sum += nums.get(i);
            }
        }
        return sum;
    }
}

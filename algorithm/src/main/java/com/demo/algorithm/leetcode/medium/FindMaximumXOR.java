package com.demo.algorithm.leetcode.medium;

/**
 * create by chenglong on 2023/11/4
 * description : 数组中两个数的最大异或值
 *
 * 给你一个整数数组nums，返回nums[i] XOR nums[j]的最大运算结果，其中 0≤i≤j<n。
 *
 * 示例 1：
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 *
 * 示例 2：
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 *
 * 提示：
 * 1 <= nums.length <= 2 * 10^5
 * 0 <= nums[i] <= 2^31 - 1
 */
public class FindMaximumXOR {

    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        if (n == 2) {
            return nums[0] ^ nums[1];
        }
        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (i == maxIndex) {
                continue;
            }
            int tem = nums[maxIndex] ^ nums[i];
            if (tem > max) {
                max = tem;
            }
        }
        return max;
    }
}

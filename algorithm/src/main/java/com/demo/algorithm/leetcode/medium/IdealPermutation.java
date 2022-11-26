package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/11/16
 * @author chenglong
 * description : 全局倒置与局部倒置
 *
 * 给你一个长度为n的整数数组nums，表示由范围[0, n - 1]内所有整数组成的一个排列。
 *
 * 全局倒置的数目等于满足下述条件不同下标对(i, j)的数目：
 * 0 <= i < j < n
 * nums[i] > nums[j]
 *
 * 局部倒置的数目等于满足下述条件的下标i的数目：
 * 0 <= i < n - 1
 * nums[i] > nums[i + 1]
 *
 * 当数组nums中全局倒置的数量等于局部倒置的数量时，返回true；否则返回false。
 *
 * 示例 1：
 * 输入：nums = [1,0,2]
 * 输出：true
 * 解释：有1个全局倒置，和1个局部倒置。
 *
 * 示例 2：
 * 输入：nums = [1,2,0]
 * 输出：false
 * 解释：有2个全局倒置，和1个局部倒置。
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * 0 <= nums[i] < n
 * nums中的所有整数互不相同
 * nums是范围 [0, n-1] 内所有数字组成的一个排列
 */
public class IdealPermutation {

    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return true;
        }
        int min = nums[n - 1];
        for (int i = n - 3; i >= 0; i--) {
            if (nums[i] > min) {
                return false;
            }
            if (nums[i + 1] < min) {
                min = nums[i + 1];
            }
        }
        return true;
    }
}

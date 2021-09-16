package com.demo.algorithm.leetcode.easy;

import java.util.Date;

/**
 * create by chenglong on 9/16/21
 * description : 存在重复元素 II
 *
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 *
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class SameNumber2 {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length < 2) {
            return false;
        }
        int length = nums.length;
        for (int i = 0; i < length - 1; i++) {
            if (findSameNum(nums, i + 1, i + k, nums[i])) {
                return true;
            }
        }
        return false;
    }

    private static boolean findSameNum(int[] datas, int start, int end, int num) {
        if (end > datas.length - 1) {
            end = datas.length - 1;
        }
        for (int i = start; i <= end; i++) {
            if (datas[i] == num) {
                return true;
            }
        }
        return false;
    }
}

package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/25.
 * description : 剑指Offer03. 数组中重复的数字
 *
 *
 * 找出数组中重复的数字。
 * 在一个长度为n的数组nums里的所有数字都在0～n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *
 * 限制：
 * 2 <= n <= 100000
 */
public class FindRepeatNumber {

    public int findRepeatNumber(int[] nums) {
        int length = nums.length;
        if (length == 2) {
            return nums[0];
        }
        int[] counts = new int[100001];
        for (int i = 0; i < length; i++) {
            counts[nums[i]]++;
            if (counts[nums[i]] > 1) {
                return nums[i];
            }
        }
        return 0;
    }
}

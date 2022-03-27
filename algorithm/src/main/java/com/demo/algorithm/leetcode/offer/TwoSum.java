package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer57. 和为s的两个数字
 *
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 *
 * 示例 2：
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *
 * 限制：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i]<= 10^6
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        if (length == 1) {
            return new int[0];
        }
        if (nums[0] * 2 >= target || nums[length - 1] * 2 <= target) {
            return new int[0];
        }
        for (int i = 0; i < length - 1; i++) {
            int start = nums[i];
            int find = target - start;
            if (find <= start) {
                break;
            }
            int index = findIndex(nums, i + 1, find);
            if (index != -1) {
                return new int[]{start, nums[index]};
            }
        }
        return new int[0];
    }

    private int findIndex(int[] nums, int start, int target) {
        int end = nums.length - 1;
        if (target < nums[start] || target > nums[end]) {
            return -1;
        }
        if (target == nums[start]) {
            return start;
        }
        if (target == nums[end]) {
            return end;
        }
        while (start <= end) {
            int middle = ((end - start) >> 1) + start;
            if (nums[middle] == target) {
                return middle;
            }
            if (target > nums[middle]) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }
}

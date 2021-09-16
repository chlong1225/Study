package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 9/4/21
 * description : 搜索插入位置
 *
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 *
 * 示例 4:
 * 输入: nums = [1,3,5,6], target = 0
 * 输出: 0
 *
 * 示例 5:
 * 输入: nums = [1], target = 0
 * 输出: 0
 */
public class ArrayInsert {

    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int min = 0;
        int max = nums.length - 1;
        int middle;
        while (max - min >= 2) {
            if (target <= nums[min]) {
                return min;
            }
            if (target > nums[max]) {
                return max + 1;
            }
            if (target == nums[max]) {
                return max;
            }
            middle = (min + max) / 2;
            if (target > nums[middle]) {
                min = middle;
            } else if (target < nums[middle]) {
                max = middle;
            } else {
                return middle;
            }
        }
        if (target <= nums[min]) {
            return min;
        }
        if (target == nums[max]) {
            return max;
        }
        if (target > nums[max]) {
            return max + 1;
        }
        return min + 1;
    }
}

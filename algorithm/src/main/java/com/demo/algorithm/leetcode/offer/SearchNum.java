package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/25.
 * description : 剑指Offer53 - I. 在排序数组中查找数字I
 *
 * 统计一个数字在排序数组中出现的次数。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 *
 * 示例2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9<= nums[i]<= 10^9
 * nums是一个非递减数组
 * -10^9<= target<= 10^9
 */
public class SearchNum {

    public int search(int[] nums, int target) {
        /**
         * 由于数组是有序的，可以通过二分查找获取target所在的位置，然后向前向后查找
         */
        int length = nums.length;
        if (length == 0) {
            return 0;
        }
        if (length == 1) {
            if (nums[0] == target) {
                return 1;
            }
            return 0;
        }
        int index = findIndex(nums, target);
        if (index == -1) {
            return 0;
        }
        int start = index;
        for (int i = index - 1; i >= 0; i--) {
            if (nums[i] != target) {
                break;
            } else {
                start = i;
            }
        }
        int end = index;
        for (int i = index + 1; i < length; i++) {
            if (nums[i] != target) {
                break;
            } else {
                end = i;
            }
        }
        return end - start + 1;
    }

    private int findIndex(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        if (target > nums[end]) {
            return -1;
        }
        if (target < nums[start]) {
            return -1;
        }
        if (target == nums[start]) {
            return start;
        }
        if (target == nums[end]) {
            return end;
        }
        while (start <= end) {
            int middle = (start + end) >> 1;
            if (nums[middle] == target) {
                return middle;
            }
            if (nums[middle] > target) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }
}

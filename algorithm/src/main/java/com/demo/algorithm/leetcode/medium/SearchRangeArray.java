package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/10/6.
 * description : 在排序数组中查找元素的第一个和最后一个位置
 *
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *  
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *  
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class SearchRangeArray {

    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length == 0) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        int index = findByBinary(nums, target);
        if (index == -1) {
            result[0] = -1;
            result[1] = -1;
            return result;
        }
        int startIndex = index;
        int endIndex = index;
        int length = nums.length;
        for (int i = index + 1; i < length; i++) {
            if (nums[i] == target) {
                endIndex = i;
            } else {
                break;
            }
        }
        for (int i = index - 1; i >= 0; i--) {
            if (nums[i] == target) {
                startIndex = i;
            } else {
                break;
            }
        }
        result[0] = startIndex;
        result[1] = endIndex;
        return result;
    }

    private int findByBinary(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        if (nums[start] > target || nums[end] < target) {
            return -1;
        }
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        return -1;
    }

}

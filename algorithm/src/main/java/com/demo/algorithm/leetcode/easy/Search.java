package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/1
 * @author chenglong
 * description : 二分查找
 *
 * 给定一个n个元素有序的（升序）整型数组nums和一个目标值target，写一个函数搜索nums中的target，如果目标值存在返回下标，否则返回-1。
 *
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 *
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *
 * 提示：
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 */
public class Search {

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        if (target < nums[start] || target > nums[end]) {
            return -1;
        }
        if (target == nums[start]) {
            return 0;
        }
        if (target == nums[end]) {
            return end;
        }
        while (start <= end) {
            int middle = (end - start) / 2 + start;
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

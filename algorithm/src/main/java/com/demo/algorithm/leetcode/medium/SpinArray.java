package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/10/6.
 * description : 搜索旋转排序数组
 * <p>
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * <p>
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 * <p>
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 * <p>
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 *  
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 * 进阶：你可以设计一个时间复杂度为 O(log n) 的解决方案吗？
 */
public class SpinArray {

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int result = -1;
        //使用二分法查找
        int start = 0;
        int end = length - 1;
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (nums[start] < nums[middle]) {
                //start ~ middle 之间有序
                int index = findByBinAry(nums, start, middle, target);
                if (index != -1) {
                    return index;
                }
                start = middle + 1;
            } else {
                //middle ~ end之间有序
                int index = findByBinAry(nums, middle, end, target);
                if (index != -1) {
                    return index;
                }
                end = middle - 1;
            }
        }
        return result;
    }

    private static int findByBinAry(int[] nums, int start, int end, int target) {
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

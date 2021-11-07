package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/11/7.
 * description :  搜索旋转排序数组II
 *
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 * 示例 1：
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 *
 * 示例 2：
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 *  
 * 提示：
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 *  
 * 进阶：
 * 这是 搜索旋转排序数组的延伸题目，本题中的nums可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 */
public class SearchArray2 {

    public boolean search(int[] nums, int target) {
        int length = nums.length;
        if (length == 1) {
            return nums[0] == target;
        }
        if (length == 2) {
            return nums[0] == target || nums[1] == target;
        }
        int min = 0;
        int max = length - 1;
        while (min <= max) {
            if (nums[min] == target || nums[max] == target) {
                return true;
            }
            int middle = (min + max) >> 1;
            if (nums[middle] == target) {
                return true;
            }
            if (nums[middle] > target) {
                if (nums[middle] > nums[min]) {
                    //在min~middle之间数据升序,target使用二分法在min~middle之间查找
                    int index = findIndexByTarget(min, middle, nums, target);
                    if (index != -1) {
                        return true;
                    }
                    //左边有序数组找不到，在右边查找
                    min = middle + 1;
                } else if (nums[middle] == nums[min]) {
                    min++;
                    max--;
                } else {
                    //在middle~max之间数据升序,target在middle两边都可能.
                    int index = findIndexByTarget(middle, max, nums, target);
                    if (index != -1) {
                        return true;
                    }
                    //右边找不到,在左边查找
                    max = middle - 1;
                }
            } else {
                if (nums[middle] > nums[min]) {
                    //在min~middle之间数据升序,target不可能在middle左边,只能在右边查找
                    min = middle + 1;
                } else if (nums[middle] == nums[min]) {
                    min++;
                    max--;
                } else {
                    //在middle~max之间数据升序，target在middle两边都可能
                    int index = findIndexByTarget(middle, max, nums, target);
                    if (index != -1) {
                        return true;
                    }
                    //右边找不到，在左边查找
                    max = middle - 1;
                }
            }
        }
        return false;
    }

    //使用二分法在有序数组中查找值
    private int findIndexByTarget(int start, int end, int[] nums, int target) {
        while (start <= end) {
            if (nums[start] == target) {
                return start;
            }
            if (nums[end] == target) {
                return end;
            }
            if (nums[start] > target || nums[end] < target) {
                return -1;
            }
            int middle = (start + end) >> 1;
            if (nums[middle] == target) {
                return middle;
            } else if (nums[middle] < target) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return -1;
    }
}

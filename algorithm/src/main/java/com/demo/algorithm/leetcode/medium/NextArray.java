package com.demo.algorithm.leetcode.medium;

import java.util.Arrays;

/**
 * Created by chl on 2021/10/4.
 * description : 下一个排列
 *
 * 实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列（即，组合出下一个更大的整数）。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 *
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *
 * 示例 4：
 * 输入：nums = [1]
 * 输出：[1]
 *  
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 */
public class NextArray {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int length = nums.length;
        boolean isReverse = true;
        for (int i = length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                isReverse = false;
                //需要交换i-1与i~length-1之间比它大的最小值
                int index = findMin(nums, i, nums[i - 1]);
                int tem = nums[i - 1];
                nums[i - 1] = nums[index];
                nums[index] = tem;
                //剩下的i~length-1进行排序
                Arrays.sort(nums, i, length);
                break;
            }
        }
        if (isReverse) {
            //数组反转
            int middle = (length + 1) / 2;
            int tem;
            for (int i = 0; i < middle; i++) {
                tem = nums[i];
                nums[i] = nums[length - 1 - i];
                nums[length - 1 - i] = tem;
            }
        }
    }

    private int findMin(int[] nums, int startIndex, int compare) {
        int length = nums.length;
        int index = startIndex;
        for (int i = startIndex + 1; i < length; i++) {
            if (nums[i] > compare && nums[i] < nums[index]) {
                index = i;
            }
        }
        return index;
    }
}

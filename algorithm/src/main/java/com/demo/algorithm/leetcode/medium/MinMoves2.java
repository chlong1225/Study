package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/5/19
 * @author chenglong
 * description : 最少移动次数使数组元素相等II
 *
 * 给你一个长度为n的整数数组nums，返回使所有数组元素相等需要的最少移动数。
 * 在一步操作中，你可以使数组中的一个元素加1或者减1 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：
 * 只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 *
 * 示例 2：
 * 输入：nums = [1,10,2,9]
 * 输出：16
 *
 * 提示：
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class MinMoves2 {

    private int[] dates;
    private int[] marks;

    public int minMoves2(int[] nums) {
        int length = nums.length;
        dates = nums;
        marks = new int[length];
        //1，排序数据
        sortDates(0, length - 1);
        int target = dates[(length - 1) / 2];
        int sum = 0;
        //2，遍历统计
        for (int i = 0; i < length; i++) {
            sum += Math.abs(dates[i] - target);
        }
        return sum;
    }

    //使用归并排序
    private void sortDates(int start, int end) {
        //1，拆分数据
        if (start >= end) {
            return;
        }
        int middle = (end - start) / 2 + start;
        sortDates(start, middle);
        sortDates(middle + 1, end);
        //2，合并数据区间：[start~middle]与[middle+1~end]
        int index = start;
        int index1 = start;
        int index2 = middle + 1;
        int end1 = middle + 1;
        int end2 = end + 1;
        while (index1 < end1 && index2 < end2) {
            if (dates[index1] > dates[index2]) {
                marks[index] = dates[index2];
                index2++;
            } else {
                marks[index] = dates[index1];
                index1++;
            }
            index++;
        }
        if (index1 == end1) {
            for (int i = index2; i < end2; i++) {
                marks[index] = dates[i];
                index++;
            }
        } else {
            for (int i = index1; i < end1; i++) {
                marks[index] = dates[i];
                index++;
            }
        }
        for (int i = start; i <= end; i++) {
            dates[i] = marks[i];
            marks[i] = 0;
        }
    }
}

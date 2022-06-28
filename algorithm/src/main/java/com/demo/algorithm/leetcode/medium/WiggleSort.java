package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/6/28
 * @author chenglong
 * description : 摆动排序II
 *
 * 给你一个整数数组nums，将它重新排列成nums[0] < nums[1] > nums[2] < nums[3]...的顺序。
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 *
 * 示例 1：
 * 输入：nums = [1,5,1,1,6,4]
 * 输出：[1,6,1,5,1,4]
 * 解释：[1,4,1,5,1,6] 同样是符合题目要求的结果，可以被判题程序接受。
 *
 * 示例 2：
 * 输入：nums = [1,3,2,2,3,1]
 * 输出：[2,3,1,3,1,2]
 *
 * 提示：
 * 1 <= nums.length <= 5 * 10^4
 * 0 <= nums[i] <= 5000
 * 题目数据保证，对于给定的输入 nums ，总能产生满足题目要求的结果
 *
 * 进阶：你能用O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 */
public class WiggleSort {

    public void wiggleSort(int[] nums) {
        int[] counts = new int[5001];
        int length = nums.length;
        if (length == 1) {
            return;
        }
        //1，统计数字出现的次数
        for (int i = 0; i < length; i++) {
            counts[nums[i]]++;
        }
        //2，按照奇偶位置重新赋值
        int index = counts.length - 1;
        for (int i = 1; i < length; i += 2) {
            while (counts[index] == 0) {
                index--;
            }
            nums[i] = index;
            counts[index]--;
        }
        for (int i = 0; i < length; i += 2) {
            while (counts[index] == 0) {
                index--;
            }
            nums[i] = index;
            counts[index]--;
        }
    }
}

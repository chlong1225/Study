package com.demo.algorithm.leetcode.medium;

/**
 * create by chenglong on 2023/12/12
 * description : 最长递增子序列
 *
 * 给你一个整数数组nums，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。
 * 例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7]的子序列。
 *
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 *
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 *
 * 示例 3：
 * 输入：nums = [7,7,7,7,7,7,7]
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 2500
 * -10^4 <= nums[i] <= 10^4
 *
 * 进阶：
 * 你能将算法的时间复杂度降低到 O(nlog(n)) 吗?
 */
public class LengthOfLIS {

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 1;
        }
        int[] marks = new int[n];
        marks[0] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            marks[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    //此时nums[i]可以拼接在nums[j]之后
                    marks[i] = Math.max(marks[i], marks[j] + 1);
                    if (marks[i] > max) {
                        max = marks[i];
                    }
                }
            }
        }
        return max;
    }
}

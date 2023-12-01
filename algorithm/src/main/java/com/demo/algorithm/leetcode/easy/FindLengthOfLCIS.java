package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/12/1
 * @author chenglong
 * description : 最长连续递增序列
 *
 * 给定一个未经排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度。
 * 连续递增的子序列可以由两个下标l和r（l<r）确定，如果对于每个l <= i < r，都有nums[i] < nums[i + 1]，
 * 那么子序列[nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 * 示例 1：
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 *
 * 示例 2：
 * 输入：nums = [2,2,2,2,2]
 * 输出：1
 * 解释：最长连续递增序列是 [2], 长度为1。
 *
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 */
public class FindLengthOfLCIS {

    public int findLengthOfLCIS(int[] nums) {
        int max = 1;
        int n = nums.length;
        int start = 0;
        int end = start;
        while (end + 1 < n) {
            if (nums[end + 1] > nums[end]) {
                end++;
            } else {
                int count = end - start + 1;
                if (count > max) {
                    max = count;
                }
                start = end + 1;
                end = start;
            }
        }
        int count = end - start + 1;
        if (count > max) {
            max = count;
        }
        return max;
    }
}

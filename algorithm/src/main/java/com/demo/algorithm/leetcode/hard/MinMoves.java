package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/12/19
 * @author chenglong
 * description : 得到连续K个1的最少相邻交换次数
 *
 * 给你一个整数数组nums和一个整数k。nums仅包含0和1。每一次移动，你可以选择相邻两个数字并将它们交换。
 * 请你返回使nums中包含k个连续1的最少交换次数。
 *
 * 示例 1：
 * 输入：nums = [1,0,0,1,0,1], k = 2
 * 输出：1
 * 解释：在第一次操作时，nums 可以变成 [1,0,0,0,1,1] 得到连续两个 1 。
 *
 * 示例 2：
 * 输入：nums = [1,0,0,0,0,0,1,1], k = 3
 * 输出：5
 * 解释：通过 5 次操作，最左边的 1 可以移到右边直到 nums 变为 [0,0,0,0,0,1,1,1] 。
 *
 * 示例 3：
 * 输入：nums = [1,1,0,1], k = 2
 * 输出：0
 * 解释：nums 已经有连续 2 个 1 了。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * nums[i]要么是0，要么是1。
 * 1 <= k <= sum(nums)
 */
public class MinMoves {

    public int minMoves(int[] nums, int k) {

        return 0;
    }
}

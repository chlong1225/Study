package com.demo.algorithm.leetcode.easy;

/**
 * create on 2022/2/9
 * @author chenglong
 * description : 差的绝对值为K的数对数目
 *
 * 给你一个整数数组nums和一个整数k，请你返回数对(i, j)的数目，满足i < j且|nums[i] - nums[j]| == k。
 * |x|的值定义为：
 * 如果x >= 0，那么值为x。
 * 如果x < 0，那么值为-x。
 *
 * 示例 1：
 * 输入：nums = [1,2,2,1], k = 1
 * 输出：4
 * 解释：差的绝对值为 1 的数对为：
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 * - [1,2,2,1]
 *
 * 示例 2：
 * 输入：nums = [1,3], k = 3
 * 输出：0
 * 解释：没有任何数对差的绝对值为 3 。
 *
 * 示例 3：
 * 输入：nums = [3,2,1,5,4], k = 2
 * 输出：3
 * 解释：差的绝对值为2的数对为：
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 * - [3,2,1,5,4]
 *
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * 1 <= k <= 99
 */
public class CountKDiff {

    public int countKDifference(int[] nums, int k) {
        int[] counts = new int[101];
        int length = nums.length;
        if (length == 1) {
            return 0;
        }
        for (int i = 0; i < length; i++) {
            counts[nums[i]]++;
        }
        int sum = 0;
        for (int i = 1; i + k <= 100; i++) {
            if (counts[i] > 0) {
                int find = counts[i + k];
                sum += (find * counts[i]);
            }
        }
        return sum;
    }
}

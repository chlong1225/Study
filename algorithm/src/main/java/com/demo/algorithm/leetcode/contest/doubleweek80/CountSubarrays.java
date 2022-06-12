package com.demo.algorithm.leetcode.contest.doubleweek80;

/**
 * Created by chl on 2022/6/12.
 * description : 统计得分小于K的子数组数目
 *
 * 一个数字的分数定义为数组之和乘以数组的长度。
 * 比方说，[1, 2, 3, 4, 5]的分数为(1 + 2 + 3 + 4 + 5)*5 = 75。
 * 给你一个正整数数组nums和一个整数k，请你返回nums中分数严格小于k的非空整数子数组数目。
 * 子数组 是数组中的一个连续元素序列。
 *
 * 示例 1：
 * 输入：nums = [2,1,4,3,5], k = 10
 * 输出：6
 * 解释：
 * 有 6 个子数组的分数小于 10 ：
 * - [2] 分数为 2 * 1 = 2 。
 * - [1] 分数为 1 * 1 = 1 。
 * - [4] 分数为 4 * 1 = 4 。
 * - [3] 分数为 3 * 1 = 3 。
 * - [5] 分数为 5 * 1 = 5 。
 * - [2,1] 分数为 (2 + 1) * 2 = 6 。
 * 注意，子数组 [1,4] 和 [4,3,5] 不符合要求，因为它们的分数分别为 10 和 36，但我们要求子数组的分数严格小于 10 。
 *
 * 示例 2：
 * 输入：nums = [1,1,1], k = 5
 * 输出：5
 * 解释：
 * 除了 [1,1,1] 以外每个子数组分数都小于 5 。
 * [1,1,1] 分数为 (1 + 1 + 1) * 3 = 9 ，大于 5 。
 * 所以总共有 5 个子数组得分小于 5 。
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= 10^15
 */
public class CountSubarrays {

    public long countSubarrays(int[] nums, long k) {
        int length = nums.length;
        if (length == 1) {
            if (nums[0] < k) {
                return 1;
            }
            return 0;
        }
        //1，获取数组的前缀和
        long[] sums = new long[length + 1];
        for (int i = 0; i < length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
        long sum = 0;
        //2，依次遍历子数组的起点
        for (int i = 0; i < length; i++) {
            //以i为起点满足条件的子数组数量
            sum += getCount(i, sums, k);
        }
        return sum;
    }

    private long getCount(int index, long[] sums, long target) {
        int m = sums.length - 1;
        int start = index;
        int end = m;
        if (sums[index + 1] - sums[index] >= target) {
            return 0;
        }
        long base = 1;
        long max = base * (m - index) * (sums[m] - sums[index]);
        if (max < target) {
            return m - index;
        }
        if (max == target) {
            return m - index - 1;
        }
        while (start < end) {
            int middle = (end - start) / 2 + start;
            long total = base * (middle + 1 - index) * (sums[middle + 1] - sums[index]);
            if (total >= target) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        //start位置时刚好大于target，之前的子数组都满足
        return start - index;
    }

    public long countSubarrays2(int[] nums, long k) {
        long total = 0;
        long sum = 0;
        int length = nums.length;
        for (int left = 0, right = 0; right < length; right++) {
            sum += nums[right];
            int count = right - left + 1;
            while (sum * count >= k) {
                sum -= nums[left++];
                count--;
            }
            total += count;
        }
        return total;
    }
}

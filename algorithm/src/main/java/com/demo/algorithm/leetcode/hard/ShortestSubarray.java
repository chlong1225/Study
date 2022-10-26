package com.demo.algorithm.leetcode.hard;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * create on 2022/10/26
 * @author chenglong
 * description : 和至少为K的最短子数组
 *
 * 给你一个整数数组nums和一个整数k，找出nums中和至少为k的最短非空子数组，并返回该子数组的长度。如果不存在这样的子数组返回-1 。
 * 子数组是数组中连续的一部分。
 *
 * 示例 1：
 * 输入：nums = [1], k = 1
 * 输出：1
 *
 * 示例 2：
 * 输入：nums = [1,2], k = 4
 * 输出：-1
 *
 * 示例 3：
 * 输入：nums = [2,-1,2], k = 3
 * 输出：3
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= 10^9
 */
public class ShortestSubarray {

    public int shortestSubarray(int[] nums, int k) {
        int length = nums.length;
        //1，统计前缀和
        long[] marks = new long[length];
        marks[0] = nums[0];
        for (int i = 1; i < length; i++) {
            marks[i] = nums[i] + marks[i - 1];
        }
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < length; i++) {
            long cur = marks[i];
            if (cur >= k) {
                if (i + 1 < min) {
                    min = i + 1;
                }
            }
            while (!stack.isEmpty() && cur - marks[stack.peekFirst()] >= k) {
                int count = i - stack.pollFirst();
                if (count < min) {
                    min = count;
                }
            }
            while (!stack.isEmpty() && marks[stack.peekLast()] >= cur) {
                stack.pollLast();
            }
            stack.offer(i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }
}

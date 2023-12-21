package com.demo.algorithm.leetcode.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * create on 2023/12/21
 * @author chenglong
 * description :  美丽塔II
 *
 * 给你一个长度为n下标从0开始的整数数组maxHeights。
 * 你的任务是在坐标轴上建n座塔。第i座塔的下标为i，高度为heights[i]。
 * 如果以下条件满足，我们称这些塔是美丽的：
 * 1 <= heights[i] <= maxHeights[i]
 * heights是一个山脉数组。
 * 如果存在下标i满足以下条件，那么我们称数组heights是一个山脉数组：
 * 对于所有 0 < j <= i ，都有 heights[j - 1] <= heights[j]
 * 对于所有 i <= k < n - 1 ，都有 heights[k + 1] <= heights[k]
 * 请你返回满足 美丽塔 要求的方案中，高度和的最大值 。
 *
 * 示例 1：
 * 输入：maxHeights = [5,3,4,1,1]
 * 输出：13
 * 解释：和最大的美丽塔方案为 heights = [5,3,3,1,1] ，这是一个美丽塔方案，因为：
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights 是个山脉数组，峰值在 i = 0 处。
 * 13 是所有美丽塔方案中的最大高度和。
 *
 * 示例 2：
 * 输入：maxHeights = [6,5,3,9,2,7]
 * 输出：22
 * 解释： 和最大的美丽塔方案为 heights = [3,3,3,9,2,2] ，这是一个美丽塔方案，因为：
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights 是个山脉数组，峰值在 i = 3 处。
 * 22 是所有美丽塔方案中的最大高度和。
 *
 * 示例 3：
 * 输入：maxHeights = [3,2,5,5,2,3]
 * 输出：18
 * 解释：和最大的美丽塔方案为 heights = [2,2,5,5,2,2] ，这是一个美丽塔方案，因为：
 * - 1 <= heights[i] <= maxHeights[i]
 * - heights 是个山脉数组，最大值在 i = 2 处。
 * 注意，在这个方案中，i = 3 也是一个峰值。
 * 18 是所有美丽塔方案中的最大高度和。
 *
 * 提示：
 * 1 <= n == maxHeights <= 10^5
 * 1 <= maxHeights[i] <= 10^9
 */
public class MaximumSumOfHeights2 {

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        //1，统计最大前缀和
        Deque<Integer> stack = new ArrayDeque<Integer>();
        long[] pre = new long[n];
        long base = 1;
        pre[0] = maxHeights.get(0);
        stack.addLast(0);
        for (int i = 1; i < n; i++) {
            int cur = maxHeights.get(i);
            while (stack.size() > 0 && maxHeights.get(stack.peekLast()) > cur) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                pre[i] = base * cur * (i + 1);
            } else {
                int preIndex = stack.peekLast();
                pre[i] = pre[preIndex] + base * cur * (i - preIndex);
            }
            stack.addLast(i);
        }
        //2，统计右边最大前缀和
        stack.clear();
        long[] last = new long[n];
        last[n - 1] = maxHeights.get(n - 1);
        stack.addLast(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            int cur = maxHeights.get(i);
            while (stack.size() > 0 && maxHeights.get(stack.peekLast()) > cur) {
                stack.pollLast();
            }
            if (stack.isEmpty()) {
                last[i] = base * cur * (n - i);
            } else {
                int lastIndex = stack.peekLast();
                last[i] = last[lastIndex] + base * cur * (lastIndex - i);
            }
            stack.addLast(i);
        }
        long max = 0;
        for (int i = 0; i < n; i++) {
            long sum = pre[i] + last[i] - maxHeights.get(i);
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}

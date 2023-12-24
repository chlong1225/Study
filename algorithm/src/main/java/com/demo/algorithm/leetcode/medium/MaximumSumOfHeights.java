package com.demo.algorithm.leetcode.medium;

import java.util.List;

/**
 * create on 2023/12/21
 * @author chenglong
 * description : 美丽塔I
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
 * 1 <= n == maxHeights <= 10^3
 * 1 <= maxHeights[i] <= 10^9
 */
public class MaximumSumOfHeights {

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long max = 0;
        for (int i = 0; i < n; i++) {
            //以当前i位置为山峰
            long sum = maxHeights.get(i);
            int pre = maxHeights.get(i);
            //向左边发散
            for (int j = i - 1; j >= 0; j--) {
                int cur = Math.min(pre, maxHeights.get(j));
                sum += cur;
                pre = cur;
            }
            //向右边发散
            pre = maxHeights.get(i);
            for (int j = i + 1; j < n; j++) {
                int cur = Math.min(pre, maxHeights.get(j));
                sum += cur;
                pre = cur;
            }
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}

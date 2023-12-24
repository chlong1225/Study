package com.demo.algorithm.leetcode.medium;

import java.util.PriorityQueue;

/**
 * create by chenglong on 2023/12/24
 * description : 移除石子使总数最小
 *
 * 给你一个整数数组piles，数组下标从0开始，其中piles[i]表示第i堆石子中的石子数量。另给你一个整数k，请你执行下述操作恰好k次：
 * 选出任一石子堆piles[i]，并从中移除floor(piles[i]/2)颗石子。
 * 注意：你可以对同一堆石子多次执行此操作。
 * 返回执行k次操作后，剩下石子的最小总数。
 * floor(x)为小于或等于x的最大整数。（即，对x向下取整）。
 *
 * 示例 1：
 * 输入：piles = [5,4,9], k = 2
 * 输出：12
 * 解释：可能的执行情景如下：
 * - 对第 2 堆石子执行移除操作，石子分布情况变成 [5,4,5] 。
 * - 对第 0 堆石子执行移除操作，石子分布情况变成 [3,4,5] 。
 * 剩下石子的总数为 12 。
 *
 * 示例 2：
 * 输入：piles = [4,3,6,7], k = 3
 * 输出：12
 * 解释：可能的执行情景如下：
 * - 对第 2 堆石子执行移除操作，石子分布情况变成 [4,3,3,7] 。
 * - 对第 3 堆石子执行移除操作，石子分布情况变成 [4,3,3,4] 。
 * - 对第 0 堆石子执行移除操作，石子分布情况变成 [2,3,3,4] 。
 * 剩下石子的总数为 12 。
 *
 * 提示：
 * 1 <= piles.length <= 10^5
 * 1 <= piles[i] <= 10^4
 * 1 <= k <= 10^5
 */
public class MinStoneSum {

    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> dates = new PriorityQueue<>((o1, o2) -> o2 - o1);
        int sum = 0;
        for (int i = 0; i < piles.length; i++) {
            dates.offer(piles[i]);
            sum += piles[i];
        }
        while (k > 0) {
            int max = dates.poll();
            int delete = max / 2;
            sum -= delete;
            dates.offer(max - delete);
            k--;
            if (delete == 0) {
                break;
            }
        }
        return sum;
    }
}

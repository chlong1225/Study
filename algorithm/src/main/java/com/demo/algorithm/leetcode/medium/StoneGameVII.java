package com.demo.algorithm.leetcode.medium;

/**
 * create on 2024/2/3
 * @author chenglong
 * description : 石子游戏VII
 *
 * 石子游戏中，爱丽丝和鲍勃轮流进行自己的回合，爱丽丝先开始。
 * 有n块石子排成一排。每个玩家的回合中，可以从行中移除最左边的石头或最右边的石头，并获得与该行中剩余石头值之和相等的得分。当没有石头可移除时，得分较高者获胜。
 * 鲍勃发现他总是输掉游戏（可怜的鲍勃他总是输），所以他决定尽力减小得分的差值。爱丽丝的目标是最大限度地扩大得分的差值。
 * 给你一个整数数组stones，其中stones[i]表示从左边开始的第i个石头的值，如果爱丽丝和鲍勃都发挥出最佳水平，请返回他们得分的差值。
 *
 * 示例 1：
 * 输入：stones = [5,3,1,4,2]
 * 输出：6
 * 解释：
 * - 爱丽丝移除 2 ，得分 5 + 3 + 1 + 4 = 13 。游戏情况：爱丽丝 = 13 ，鲍勃 = 0 ，石子 = [5,3,1,4] 。
 * - 鲍勃移除 5 ，得分 3 + 1 + 4 = 8 。游戏情况：爱丽丝 = 13 ，鲍勃 = 8 ，石子 = [3,1,4] 。
 * - 爱丽丝移除 3 ，得分 1 + 4 = 5 。游戏情况：爱丽丝 = 18 ，鲍勃 = 8 ，石子 = [1,4] 。
 * - 鲍勃移除 1 ，得分 4 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [4] 。
 * - 爱丽丝移除 4 ，得分 0 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [] 。
 * 得分的差值 18 - 12 = 6 。
 *
 * 示例 2：
 * 输入：stones = [7,90,5,1,100,10,10,2]
 * 输出：122
 *
 * 提示：
 * n == stones.length
 * 2 <= n <= 1000
 * 1 <= stones[i] <= 1000
 */
public class StoneGameVII {

    public int stoneGameVII(int[] stones) {
        int n = stones.length;
        //1，使用前缀和统计得分
        int[] sums = new int[n];
        sums[0] = stones[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + stones[i];
        }
        return getMaxScore(0, n - 1, 0, 0, sums);
    }

    private int getMaxScore(int startIndex, int endIndex, int total1, int total2, int[] sums) {
        return 0;
    }
}

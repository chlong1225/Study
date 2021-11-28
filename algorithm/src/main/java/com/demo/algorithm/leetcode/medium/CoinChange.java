package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/11/26.
 * description : 零钱兑换
 *
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *
 * 示例 4：
 * 输入：coins = [1], amount = 1
 * 输出：1
 *
 * 示例 5：
 * 输入：coins = [1], amount = 2
 * 输出：2
 *  
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange {

    private static final int MAX = 10001;

    public int coinChange(int[] coins, int amount) {
        //1，特殊情况：总金额为0，则不需要任何硬币。其它场景：如果能够组合最少需要一个
        if (amount == 0) {
            return 0;
        }
        int length = coins.length;
        //2，转换完全背包问题。定义状态数组，i表示对应组合的金额。marks[i]记录对应转换金额i的最少次数.
        int[] marks = new int[amount + 1];
        //3，初始化：
        marks[0] = 0;
        for (int i = 1; i <= amount; i++) {
            marks[i] = MAX;
        }
        for (int i = 0; i < length; i++) {
            if (coins[i] > amount) {
                continue;
            }
            int coin = coins[i];
            /**
             * 4，分析对应的状态转移方程：
             * marks[i][j] = Math.min(marks[i-1][j],marks[i][j-coin]+1)
             * i: 对应添加的硬币index
             * j: 添加i个硬币后的总价值amount
             */
            for (int j = coin; j <= amount; j++) {
                marks[j] = Math.min(marks[j], marks[j - coin] + 1);
            }
        }
        return marks[amount] == MAX ? -1 : marks[amount];
    }
}

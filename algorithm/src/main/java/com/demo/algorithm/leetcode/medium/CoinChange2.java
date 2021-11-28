package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/11/28.
 * description : 零钱兑换II
 *
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。 
 * 题目数据保证结果符合 32 位带符号整数。
 *
 * 示例 1：
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2：
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 *
 * 示例 3：
 * 输入：amount = 10, coins = [10]
 * 输出：1
 *  
 * 提示：
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 */
public class CoinChange2 {

    public int change(int amount, int[] coins) {
        if (amount == 0) {
            return 1;
        }
        int length = coins.length;
        /**
         * 定义动态规划之完全背包的状态
         * mark[i][j]:
         * i表示遍历到第i个硬币，
         * j表示组合后的金额。
         * mark[i][j]遍历到第i个硬币时，组合j金额的方案数量
         * 状态转移方程：marks[i][j] = marks[i-1][j]+marks[i-1][j-coin]+marks[i-1][j-k*coin]
         * 优化后 ： marks[i][j] = marks[i-1][j] + marks[i][j-coin]
         * 再次优化： marks[i] = marks[i]+marks[i-coin]
         */
        //1，定义状态数组：金额为0时组合方式为1
        int[] marks = new int[amount + 1];
        marks[0] = 1;
        for (int i = 0; i < length; i++) {
            int coin = coins[i];
            if (coin > amount) {
                continue;
            }
            for (int j = coin; j <= amount; j++) {
                marks[j] = marks[j] + marks[j - coin];
            }
        }
        return marks[amount];
    }
}

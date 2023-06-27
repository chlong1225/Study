package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/2/10
 * @author chenglong
 * description : 掷骰子模拟
 *
 * 有一个骰子模拟器会每次投掷的时候生成一个1到6的随机数。
 * 不过我们在使用它时有个约束，就是使得投掷骰子时，连续掷出数字i的次数不能超过rollMax[i]（i从1开始编号）。
 * 现在给你一个整数数组rollMax和一个整数n，请你来计算掷n次骰子可得到的不同点数序列的数量。
 * 假如两个序列中至少存在一个元素不同，就认为这两个序列是不同的。由于答案可能很大，所以请返回 模10^9+7之后的结果。
 *
 * 示例 1：
 * 输入：n = 2, rollMax = [1,1,2,2,2,3]
 * 输出：34
 * 解释：我们掷 2 次骰子，如果没有约束的话，共有 6 * 6 = 36 种可能的组合。但是根据 rollMax 数组，数字 1 和 2 最多连续出现一次，所以不会出现序列 (1,1) 和 (2,2)。因此，最终答案是 36-2 = 34。
 *
 * 示例 2：
 * 输入：n = 2, rollMax = [1,1,1,1,1,1]
 * 输出：30
 *
 * 示例 3：
 * 输入：n = 3, rollMax = [1,1,1,2,2,3]
 * 输出：181
 *
 * 提示：
 * 1 <= n <= 5000
 * rollMax.length == 6
 * 1 <= rollMax[i] <= 15
 */
public class DieSimulator {

    private static final int MOD = 1000000007;

    public int dieSimulator(int n, int[] rollMax) {
        if (n == 1) {
            return 6;
        }
        /**
         * 动态规划：marks[i][j][k]
         * i：次数 ； j：最近一次的骰子的大小0～5。k：骰子数为j的连续个数
         */
        int[][][] marks = new int[n + 1][6][16];
        for (int i = 0; i < 6; i++) {
            marks[1][i][1] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 6; j++) {
                //当前骰子数为j
                marks[i][j][1] = 0;
                for (int k = 0; k < 6; k++) {
                    if (j != k) {
                        for (int l = 0; l < 16; l++) {
                            marks[i][j][1] += marks[i - 1][k][l];
                            marks[i][j][1] %= MOD;
                        }
                    }
                }
                for (int k = 2; k <= rollMax[j]; k++) {
                    marks[i][j][k] = marks[i - 1][j][k - 1];
                }

            }
        }
        int sum = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 16; j++) {
                sum += marks[n][i][j];
                sum %= MOD;
            }
        }
        return sum;
    }
}

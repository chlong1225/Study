package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/10/24
 * @author chenglong
 * description : 掷骰子等于目标和的方法数
 *
 * 这里有n个一样的骰子，每个骰子上都有k个面，分别标号为1到k。
 * 给定三个整数n,k和target，返回可能的方式(从总共k^n种方式中)滚动骰子的数量，使正面朝上的数字之和等于target。
 * 答案可能很大，你需要对10^9+7取模。
 *
 * 示例 1：
 * 输入：n = 1, k = 6, target = 3
 * 输出：1
 * 解释：你扔一个有 6 个面的骰子。
 * 得到 3 的和只有一种方法。
 *
 * 示例 2：
 * 输入：n = 2, k = 6, target = 7
 * 输出：6
 * 解释：你扔两个骰子，每个骰子有 6 个面。
 * 得到 7 的和有 6 种方法：1+6 2+5 3+4 4+3 5+2 6+1。
 *
 * 示例 3：
 * 输入：n = 30, k = 30, target = 500
 * 输出：222616187
 * 解释：返回的结果必须是对10^9+7取模。
 *
 * 提示：
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 */
public class NumRollsToTarget {

    private static final int MOD = 1000000007;

    public int numRollsToTarget(int n, int k, int target) {
        //1，处理特殊场景。n个骰子最小，最大点数和
        int min = n;
        int max = k * n;
        if (target < min || target > max) {
            return 0;
        }
        if (target == min || target == max) {
            return 1;
        }
        int[][] marks = new int[n + 1][target + 1];
        //1，初始化第一个骰子的投掷
        for (int i = 1; i <= Math.min(k, target); i++) {
            marks[1][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            //2，添加一个骰子进行投掷，在原有的基础上进行统计
            for (int j = 1; j <= target; j++) {
                if (marks[i - 1][j] == 0) {
                    continue;
                }
                //此时上一次投掷点数为j，次数为marks[i-1][j]
                for (int l = 1; l <= k; l++) {
                    //新的点数
                    int newValue = j + l;
                    if (newValue > target) {
                        break;
                    }
                    marks[i][newValue] += marks[i - 1][j];
                    marks[i][newValue] %= MOD;
                }
            }
        }
        return marks[n][target];
    }
}

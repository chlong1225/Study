package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/11/23
 * @author chenglong
 * description : 多米诺和托米诺平铺
 *
 * 有两种形状的瓷砖：一种是2x1的多米诺形，另一种是形如"L" 的托米诺形。两种形状都可以旋转。
 * 给定整数n，返回可以平铺2 x n的面板的方法的数量。返回对10^9+7取模的值。
 * 平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。
 *
 * 示例 1:
 * 输入: n = 3
 * 输出: 5
 *
 * 示例 2:
 * 输入: n = 1
 * 输出: 1
 *
 * 提示：
 * 1 <= n <= 1000
 */
public class NumTilings {

    private static final int MOD = 1000000007;

    public int numTilings(int n) {
        int[][] marks = new int[n + 1][4];
        marks[0][3] = 1;
        for (int i = 1; i <= n; i++) {
            marks[i][0] = marks[i - 1][3];
            marks[i][1] = (marks[i - 1][2] + marks[i - 1][0]) % MOD;
            marks[i][2] = (marks[i - 1][1] + marks[i - 1][0]) % MOD;
            marks[i][3] = ((marks[i - 1][3] + marks[i - 1][2]) % MOD + marks[i][2]) % MOD;
        }
        return marks[n][3];
    }
}

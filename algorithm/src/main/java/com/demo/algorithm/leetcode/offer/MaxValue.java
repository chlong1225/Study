package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer47. 礼物的最大价值
 *
 * 在一个m*n的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于0）。你可以从棋盘的左上角开始拿格子里的礼物，
 * 并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * 示例 1:
 * 输入:
 * [
 *  [1,3,1],
 *  [1,5,1],
 *  [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *
 * 提示：
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 */
public class MaxValue {

    public int maxValue(int[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }
        int[][] marks = new int[m][n];
        //初始化条件
        marks[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            marks[i][0] = marks[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            marks[0][i] = marks[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                marks[i][j] = grid[i][j] + Math.max(marks[i - 1][j], marks[i][j - 1]);
            }
        }
        return marks[m - 1][n - 1];
    }
}

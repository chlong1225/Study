package com.demo.algorithm.leetcode.hard;

/**
 * create on 2023/8/10
 * @author chenglong
 * description : 下降路径最小和II
 *
 * 给你一个n x n整数矩阵grid，请你返回非零偏移下降路径数字和的最小值。
 * 非零偏移下降路径 定义为：从 grid 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
 *
 * 示例 1：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：13
 * 解释：
 * 所有非零偏移下降路径包括：
 * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 * 下降路径中数字和最小的是 [1,5,7] ，所以答案是 13 。
 *
 * 示例 2：
 * 输入：grid = [[7]]
 * 输出：7
 *
 * 提示：
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * -99 <= grid[i][j] <= 99
 */
public class MinFallingPathSum {

    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 1) {
            return grid[0][0];
        }
        int[][] marks = new int[n][n];
        for (int i = 0; i < n; i++) {
            marks[0][i] = grid[0][i];
        }
        for (int i = 1; i < n; i++) {
            //1，获取上一列两个最小值的index
            int minIndex1 = 0;
            int minIndex2 = 1;
            if (marks[i - 1][minIndex1] > marks[i - 1][minIndex2]) {
                minIndex1 = 1;
                minIndex2 = 0;
            }
            for (int j = 2; j < n; j++) {
                if (marks[i - 1][j] < marks[i - 1][minIndex1]) {
                    minIndex2 = minIndex1;
                    minIndex1 = j;
                } else if (marks[i - 1][j] < marks[i - 1][minIndex2]) {
                    minIndex2 = j;
                }
            }
            //2，依次计算当前位置的最小和
            for (int j = 0; j < n; j++) {
                if (j == minIndex1) {
                    marks[i][j] = grid[i][j] + marks[i - 1][minIndex2];
                } else {
                    marks[i][j] = grid[i][j] + marks[i - 1][minIndex1];
                }
            }
        }
        int min = marks[n - 1][0];
        for (int i = 1; i < n; i++) {
            if (marks[n - 1][i] < min) {
                min = marks[n - 1][i];
            }
        }
        return min;
    }
}

package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/7/13
 * @author chenglong
 * description : 下降路径最小和
 *
 * 给你一个n x n的方形整数数组matrix，请你找出并返回通过matrix的下降路径的最小和。
 * 下降路径可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。
 * 具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 *
 * 示例 1：
 * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 *
 * 示例 2：
 * 输入：matrix = [[-19,57],[-40,-5]]
 * 输出：-59
 * 解释：如图所示，为和最小的下降路径
 *
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 */
public class MinFallingPathSum {

    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        int[][] marks = new int[n][n];
        for (int i = 0; i < n; i++) {
            marks[0][i] = matrix[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                marks[i][j] = marks[i - 1][j];
                if (j > 0 && marks[i - 1][j - 1] < marks[i][j]) {
                    marks[i][j] = marks[i - 1][j - 1];
                }
                if (j + 1 < n && marks[i - 1][j + 1] < marks[i][j]) {
                    marks[i][j] = marks[i - 1][j + 1];
                }
                marks[i][j] += matrix[i][j];
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

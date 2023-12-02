package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/11/24
 * @author chenglong
 * description : 最大正方形
 *
 * 在一个由'0'和'1'组成的二维矩阵内，找到只包含'1'的最大正方形，并返回其面积。
 *
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 *
 * 示例 2：
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 *
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        int[][] marks = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                marks[i][0] = 1;
                max = 1;
            } else {
                marks[i][0] = 0;
            }
        }
        for (int i = 1; i < n; i++) {
            if (matrix[0][i] == '1') {
                marks[0][i] = 1;
                max = 1;
            } else {
                marks[0][i] = 0;
            }
        }
        if (m == 1 || n == 1) {
            return max;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int min = marks[i - 1][j - 1];
                    if (marks[i][j - 1] < min) {
                        min = marks[i][j - 1];
                    }
                    if (marks[i - 1][j] < min) {
                        min = marks[i - 1][j];
                    }
                    marks[i][j] = min + 1;
                    if (marks[i][j] > max) {
                        max = marks[i][j];
                    }
                } else {
                    marks[i][j] = 0;
                }
            }
        }
        return max * max;
    }
}

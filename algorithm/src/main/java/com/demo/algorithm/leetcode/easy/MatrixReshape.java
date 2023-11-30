package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/11/30
 * @author chenglong
 * description : 重塑矩阵
 *
 * 在MATLAB中，有一个非常有用的函数reshape，它可以将一个m x n矩阵重塑为另一个大小不同（r x c）的新矩阵，但保留其原始数据。
 * 给你一个由二维数组mat表示的m x n矩阵，以及两个正整数r和c，分别表示想要的重构的矩阵的行数和列数。
 * 重构后的矩阵需要将原始矩阵的所有元素以相同的行遍历顺序填充。
 * 如果具有给定参数的reshape操作是可行且合理的，则输出新的重塑矩阵；否则，输出原始矩阵。
 *
 * 示例 1：
 * 输入：mat = [[1,2],[3,4]], r = 1, c = 4
 * 输出：[[1,2,3,4]]
 *
 * 示例 2：
 * 输入：mat = [[1,2],[3,4]], r = 2, c = 4
 * 输出：[[1,2],[3,4]]
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 100
 * -1000 <= mat[i][j] <= 1000
 * 1 <= r, c <= 300
 */
public class MatrixReshape {

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }
        int[][] dates = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                //index = i*c+j <——> i1*n+j1
                int index = i * c + j;
                int x = index / n;
                int y = index % n;
                dates[i][j] = mat[x][y];
            }
        }
        return dates;
    }
}

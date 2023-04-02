package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/3/14
 * @author chenglong
 * description : 给定行和列的和求可行矩阵
 *
 * 给你两个非负整数数组rowSum和colSum，其中rowSum[i]是二维矩阵中第i行元素的和，colSum[j]是第j列元素的和。换言之你不知道矩阵里的每个元素，但是你知道每一行和每一列的和。
 * 请找到大小为rowSum.length x colSum.length的任意非负整数矩阵，且该矩阵满足rowSum和colSum的要求。
 * 请你返回任意一个满足题目要求的二维矩阵，题目保证存在 至少一个可行矩阵。
 *
 * 示例 1：
 * 输入：rowSum = [3,8], colSum = [4,7]
 * 输出：[[3,0],
 *       [1,7]]
 * 解释：
 * 第 0 行：3 + 0 = 3 == rowSum[0]
 * 第 1 行：1 + 7 = 8 == rowSum[1]
 * 第 0 列：3 + 1 = 4 == colSum[0]
 * 第 1 列：0 + 7 = 7 == colSum[1]
 * 行和列的和都满足题目要求，且所有矩阵元素都是非负的。
 * 另一个可行的矩阵为：[[1,2],
 *                   [3,5]]
 *
 * 示例 2：
 * 输入：rowSum = [5,7,10], colSum = [8,6,8]
 * 输出：[[0,5,0],
 *       [6,1,0],
 *       [2,0,8]]
 *
 * 示例 3：
 * 输入：rowSum = [14,9], colSum = [6,9,8]
 * 输出：[[0,9,5],
 *       [6,0,3]]
 *
 * 示例 4：
 * 输入：rowSum = [1,0], colSum = [1]
 * 输出：[[1],
 *       [0]]
 *
 * 示例 5：
 * 输入：rowSum = [0], colSum = [0]
 * 输出：[[0]]
 *
 * 提示：
 * 1 <= rowSum.length, colSum.length <= 500
 * 0 <= rowSum[i], colSum[i] <= 10^8
 * sum(rowSum) == sum(colSum)
 */
public class RestoreMatrix {

    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[][] result = new int[m][n];
        if (m == 1 && n == 1) {
            result[0][0] = rowSum[0];
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = Math.min(colSum[j], rowSum[i]);
                colSum[j] -= result[i][j];
                rowSum[i] -= result[i][j];
            }
        }
        return result;
    }

    public int[][] restoreMatrix2(int[] rowSum, int[] colSum) {
        int m = rowSum.length;
        int n = colSum.length;
        int[][] result = new int[m][n];
        if (m == 1 && n == 1) {
            result[0][0] = rowSum[0];
        }
        int i = 0;
        int j = 0;
        while (i < m || j < n) {
            if (i == m) {
                if (colSum[j] > 0) {
                    result[i - 1][j] = colSum[j];
                }
                j++;
            } else if (j == n) {
                if (rowSum[i] > 0) {
                    result[i][j - 1] = rowSum[i];
                }
                i++;
            } else {
                if (rowSum[i] == 0) {
                    i++;
                    continue;
                }
                if (colSum[j] == 0) {
                    j++;
                    continue;
                }
                if (rowSum[i] > colSum[j]) {
                    result[i][j] = colSum[j];
                    rowSum[i] -= colSum[j];
                    colSum[j] = 0;
                    j++;
                } else if (rowSum[i] == colSum[j]) {
                    result[i][j] = rowSum[i];
                    rowSum[i] = 0;
                    colSum[j] = 0;
                    i++;
                    j++;
                } else {
                    result[i][j] = rowSum[i];
                    colSum[j] -= rowSum[i];
                    rowSum[i] = 0;
                    i++;
                }
            }
        }
        return result;
    }
}

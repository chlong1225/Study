package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/10/22.
 * description : 最大矩形
 *
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 *
 * 示例 2：
 * 输入：matrix = []
 * 输出：0
 *
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 * 示例 4：
 * 输入：matrix = [["1"]]
 * 输出：1
 *
 * 示例 5：
 * 输入：matrix = [["0","0"]]
 * 输出：0
 *  
 * 提示：
 * rows == matrix.length
 * cols == matrix[0].length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 */
public class MaxRectangle {

    public static int maximalRectangle(char[][] matrix) {
        //1,特殊条件的处理
        if (matrix == null) {
            return 0;
        }
        //行数
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        //列数
        int n = matrix[0].length;
        if (n == 0) {
            return 0;
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    //获取顶点最大的矩形
                    int result = getMaxRectangleByPoint(i, j, matrix, max);
                    if (result > max) {
                        max = result;
                    }
                }
            }
        }
        return max;
    }

    private static int getMaxRectangleByPoint(int i, int j, char[][] matrix, int max) {
        int m = matrix.length;
        int n = matrix[0].length;
        int min = n;
        for (int k = i; k < m; k++) {
            int count = 0;
            for (int l = j; l < n; l++) {
                if (matrix[k][l] == '1') {
                    count++;
                    if (count >= min) {
                        break;
                    }
                } else {
                    break;
                }
            }
            if (count == 0) {
               break;
            }
            min = count;
            int are = (k - i + 1) * min;
            if (are > max) {
                max = are;
            }
            if (min * (m - i) <= max) {
                return max;
            }
        }
        return max;
    }
}

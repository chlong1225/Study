package com.demo.algorithm.leetcode.medium;

/**
 * create on 11/1/21
 * @author chenglong
 * description : 矩阵置零
 *
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 *
 * 进阶：
 * 一个直观的解决方案是使用  O(mn) 的额外空间，但这并不是一个好的解决方案。
 * 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。
 * 你能想出一个仅使用常量空间的解决方案吗？
 *  
 * 示例 1：
 * 输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：[[1,0,1],[0,0,0],[1,0,1]]
 *
 * 示例 2：
 * 输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * 输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 *  
 * 提示：
 * m == matrix.length
 * n == matrix[0].length
 * 1 <= m, n <= 200
 * -231 <= matrix[i][j] <= 231 - 1
 */
public class MatrixZero {

    public void setZeroes(int[][] matrix) {
        //记录首行是否有零
        boolean isFirstRow = false;
        //记录首列是否有零
        boolean isFirstColumn = false;
        int row = matrix.length;
        int column = matrix[0].length;
        //1，先统计首行与首列
        for (int i = 0; i < column; i++) {
            if (matrix[0][i] == 0) {
                isFirstRow = true;
                break;
            }
        }
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == 0) {
                isFirstColumn = true;
                break;
            }
        }
        //2，处理单行或单列数据
        if (row == 1) {
            if (isFirstRow) {
                for (int i = 0; i < column; i++) {
                    matrix[0][i] = 0;
                }
            }
            return;
        }
        if (column == 1) {
            if (isFirstColumn) {
                for (int i = 0; i < row; i++) {
                    matrix[i][0] = 0;
                }
            }
            return;
        }
        //3，统计行数据
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    break;
                }
            }
        }
        //4，统计列数据
        for (int i = 1; i < column; i++) {
            for (int j = 0; j < row; j++) {
                if (matrix[j][i] == 0) {
                    matrix[0][i] = 0;
                    break;
                }
            }
        }
        //5，置零操作
        for (int i = 1; i < row; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < column; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int i = 1; i < column; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < row; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        if (isFirstRow) {
            for (int i = 0; i < column; i++) {
                matrix[0][i] = 0;
            }
        }
        if (isFirstColumn) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}

package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/10/25.
 * description : 旋转图像
 *
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 示例 3：
 * 输入：matrix = [[1]]
 * 输出：[[1]]
 *
 * 示例 4：
 * 输入：matrix = [[1,2],[3,4]]
 * 输出：[[3,1],[4,2]]
 *  
 * 提示：
 * matrix.length == n
 * matrix[i].length == n
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 */
public class RotateArray {

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) {
            return;
        }
        int count = n / 2;
        for (int i = 0; i < count; i++) {
            int length = n - 2 * i;
            int[] tem = new int[length];
            //上方的存放tem
            for (int j = 0; j < length; j++) {
                tem[j] = matrix[i][i + j];
            }
            //左边旋转到上方
            for (int j = 0; j < length; j++) {
                matrix[i][i + j] = matrix[n - 1 - i - j][i];
            }
            //底部的旋转到左边
            for (int j = 0; j < length; j++) {
                matrix[j + i][i] = matrix[n - 1 - i][i + j];
            }
            //右边的旋转到底部
            for (int j = 0; j < length; j++) {
                matrix[n - 1 - i][i + j] = matrix[n - 1 - i - j][n - 1 - i];
            }
            //tem中存放的旋转到右边
            for (int j = 0; j < length; j++) {
                matrix[i + j][n - 1 - i] = tem[j];
            }
        }
    }
}

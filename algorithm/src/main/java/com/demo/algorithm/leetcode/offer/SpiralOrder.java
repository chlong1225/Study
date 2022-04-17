package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/4/17.
 * description : 剑指Offer29. 顺时针打印矩阵
 *
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix =[[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 限制：
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length<= 100
 */
public class SpiralOrder {

    public int[] spiralOrder(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return new int[0];
        }
        int n = matrix[0].length;
        if (n == 0) {
            return new int[0];
        }
        int[] result = new int[m * n];
        int index = 0;
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        while (left <= right && top <= bottom) {
            //从左到有
            for (int i = left; i <= right; i++) {
                result[index] = matrix[top][i];
                index++;
            }
            top++;
            if (top > bottom) {
                break;
            }
            //从上到下
            for (int i = top; i <= bottom; i++) {
                result[index] = matrix[i][right];
                index++;
            }
            right--;
            if (left > right) {
                break;
            }
            //从右到左
            for (int i = right; i >= left; i--) {
                result[index] = matrix[bottom][i];
                index++;
            }
            bottom--;
            if (top > bottom) {
                break;
            }
            //从下到上
            for (int i = bottom; i >= top; i--) {
                result[index] = matrix[i][left];
                index++;
            }
            left++;
        }
        return result;
    }
}

package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/10/28.
 * description : 螺旋矩阵II
 *
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *  
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *  
 * 提示：
 * 1 <= n <= 20
 */
public class SpiralMatrix2 {

    public static int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int index = 0;
        int max = n * n;
        while (left <= right && top <= bottom) {
            for (int i = left; i <= right; i++) {
                index++;
                result[top][i] = index;
            }
            if (index == max) {
                break;
            }
            for (int i = top + 1; i <= bottom; i++) {
                index++;
                result[i][right] = index;
            }
            if (index == max) {
                break;
            }
            for (int i = right - 1; i >= left; i--) {
                index++;
                result[bottom][i] = index;
            }
            if (index == max) {
                break;
            }
            for (int i = bottom - 1; i >= top + 1; i--) {
                index++;
                result[i][left] = index;
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return result;
    }
}

package com.demo.algorithm.leetcode.medium;

/**
 * create on 2022/6/14
 * @author chenglong
 * description : 对角线遍历
 *
 * 给你一个大小为m x n的矩阵mat，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 *
 * 示例 2：
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * -10^5 <= mat[i][j] <= 10^5
 */
public class FindDiagonalOrder {

    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] result = new int[m * n];
        int index = 0;
        int length = m + n - 2;
        //是否从左边开始
        result[index++] = mat[0][0];
        boolean isLeft = false;
        for (int i = 1; i <= length; i++) {
            if (isLeft) {
                int min = Math.max(0, i - m + 1);
                int max = Math.min(i + 1, n);
                for (int x = min; x < max; x++) {
                    result[index++] = mat[i - x][x];
                }
            } else {
                int max = Math.min(i, n - 1);
                int min = Math.max(i - m, -1);
                for (int x = max; x > min; x--) {
                    result[index++] = mat[i - x][x];
                }
            }
            isLeft = !isLeft;
        }
        return result;
    }
}

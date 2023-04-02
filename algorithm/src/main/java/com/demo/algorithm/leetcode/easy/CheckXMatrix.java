package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/1/31
 * @author chenglong
 * description : 判断矩阵是否是一个X矩阵
 *
 * 如果一个正方形矩阵满足下述全部条件，则称之为一个X矩阵 ：
 * 矩阵对角线上的所有元素都不是0
 * 矩阵中所有其他元素都是0
 * 给你一个大小为 n x n 的二维整数数组grid，表示一个正方形矩阵。如果grid是一个X矩阵，返回true；否则返回false。
 *
 * 示例 1：
 * 输入：grid = [[2,0,0,1],[0,3,1,0],[0,5,2,0],[4,0,0,2]]
 * 输出：true
 * 解释：矩阵如上图所示。
 * X 矩阵应该满足：绿色元素（对角线上）都不是 0 ，红色元素都是 0 。
 * 因此，grid 是一个X矩阵。
 *
 * 示例 2：
 * 输入：grid = [[5,7,0],[0,3,1],[0,5,0]]
 * 输出：false
 * 解释：矩阵如上图所示。
 * X 矩阵应该满足：绿色元素（对角线上）都不是 0 ，红色元素都是 0 。
 * 因此，grid 不是一个 X 矩阵。
 *
 * 提示：
 * n == grid.length == grid[i].length
 * 3 <= n <= 100
 * 0 <= grid[i][j] <= 105
 */
public class CheckXMatrix {

    public boolean checkXMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j || i + j == n - 1) {
                    //对角线上的元素
                    if (grid[i][j] == 0) {
                        return false;
                    }
                } else {
                    if (grid[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

package com.demo.algorithm.leetcode.easy;

/**
 * create on 2023/3/1
 * @author chenglong
 * description : 矩阵中的局部最大值
 *
 * 给你一个大小为 n x n的整数矩阵grid 。
 * 生成一个大小为(n - 2) x (n - 2) 的整数矩阵 maxLocal ，并满足：
 * maxLocal[i][j]等于grid中以i + 1 行和j + 1列为中心的3 x 3矩阵中的最大值 。
 * 换句话说，我们希望找出grid中每个3 x 3 矩阵中的最大值。
 * 返回生成的矩阵。
 *
 * 示例 1：
 * 输入：grid = [[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]]
 * 输出：[[9,9],[8,6]]
 * 解释：原矩阵和生成的矩阵如上图所示。
 * 注意，生成的矩阵中，每个值都对应 grid 中一个相接的 3 x 3 矩阵的最大值。
 *
 * 示例 2：
 * 输入：grid = [[1,1,1,1,1],[1,1,1,1,1],[1,1,2,1,1],[1,1,1,1,1],[1,1,1,1,1]]
 * 输出：[[2,2,2],[2,2,2],[2,2,2]]
 * 解释：注意，2 包含在 grid 中每个 3 x 3 的矩阵中。
 *
 * 提示：
 * n == grid.length == grid[i].length
 * 3 <= n <= 100
 * 1 <= grid[i][j] <= 100
 */
public class LargestLocal {

    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] result = new int[n - 2][n - 2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = 0; j < n - 2; j++) {
                result[i][j] = getMax(i, j, grid);
            }
        }
        return result;
    }

    private int getMax(int i, int j, int[][] grid) {
        int max = grid[i][j];
        for (int k = i; k < i + 3; k++) {
            for (int l = j; l < j + 3; l++) {
                if (grid[k][l] > max) {
                    max = grid[k][l];
                }
            }
        }
        return max;
    }
}

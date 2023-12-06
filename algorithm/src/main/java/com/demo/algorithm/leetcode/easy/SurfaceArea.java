package com.demo.algorithm.leetcode.easy;

/**
 * create by chenglong on 2023/12/3
 * description : 三维形体的表面积
 *
 * 给你一个n * n的网格grid，上面放置着一些1 x 1 x 1的正方体。每个值v=grid[i][j] 表示v个正方体叠放在对应单元格(i, j)上。
 * 放置好正方体后，任何直接相邻的正方体都会互相粘在一起，形成一些不规则的三维形体。
 * 请你返回最终这些形体的总表面积。
 * 注意：每个形体的底面也需要计入表面积中。
 *
 * 示例 1：
 * 输入：grid = [[1,2],[3,4]]
 * 输出：34
 *
 * 示例 2：
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：32
 *
 * 示例 3：
 * 输入：grid = [[2,2,2],[2,1,2],[2,2,2]]
 * 输出：46
 *
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] <= 50
 */
public class SurfaceArea {

    public int surfaceArea(int[][] grid) {
        int n = grid.length;
        if (n == 1) {
            return 2 + 4 * grid[0][0];
        }
        int sum = 0;
        //1，添加底部+顶部的面积
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    sum += 2;
                }
            }
        }
        //2，添加四周的表面积
        for (int i = 0; i < n; i++) {
            sum += grid[0][i];
            sum += grid[n - 1][i];
            sum += grid[i][0];
            sum += grid[i][n - 1];
        }
        //3，添加中间不规则的表面积
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                sum += Math.abs(grid[i][j] - grid[i][j - 1]);
                sum += Math.abs(grid[j][i] - grid[j - 1][i]);
            }
        }
        return sum;
    }
}

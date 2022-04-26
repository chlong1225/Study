package com.demo.algorithm.leetcode.easy;

/**
 * Created by chl on 2022/4/26.
 * description : 三维形体投影面积
 *
 * 在n x n的网格grid中，我们放置了一些与 x，y，z 三轴对齐的1 x 1 x 1立方体。
 * 每个值v = grid[i][j]表示 v个正方体叠放在单元格(i, j)上。
 * 现在，我们查看这些立方体在xy、yz和 zx平面上的投影。
 * 投影就像影子，将 三维 形体映射到一个 二维 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。
 * 返回 所有三个投影的总面积 。
 *
 * 示例 1：
 * 输入：[[1,2],[3,4]]
 * 输出：17
 * 解释：这里有该形体在三个轴对齐平面上的三个投影(“阴影部分”)。
 *
 * 示例2:
 * 输入：grid = [[2]]
 * 输出：5
 *
 * 示例 3：
 * 输入：[[1,0],[0,2]]
 * 输出：8
 *
 * 提示：
 * n == grid.length == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] <= 50
 */
public class ProjectionArea {

    public int projectionArea(int[][] grid) {
        int n = grid.length;
        //统计x,y平面上的面积
        int count = 0;
        //统计x方向的最大高度，对应y，z平面投影
        int[] countX = new int[n];
        //统计y方向的最大高度，对应x，z平面投影
        int[] countY = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] > 0) {
                    count++;
                    if (i == 0) {
                        countY[j] = grid[0][j];
                    } else {
                        if (countY[j] < grid[i][j]) {
                            countY[j] = grid[i][j];
                        }
                    }
                    if (j == 0) {
                        countX[i] = grid[i][0];
                    } else {
                        if (countX[i] < grid[i][j]) {
                            countX[i] = grid[i][j];
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            count += countX[i];
            count += countY[i];
        }
        return count;
    }
}

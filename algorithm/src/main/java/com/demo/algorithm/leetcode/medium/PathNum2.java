package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/10/31.
 * description : 不同路径II
 *
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 示例 1：
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 示例 2：
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 *
 * 提示：
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] 为 0 或 1
 */
public class PathNum2 {

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        int[][] result = new int[m][n];
        //1,初始化边界条件
        result[0][0] = 1;
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1) {
                result[i][0] = 0;
            } else {
                if (result[i - 1][0] == 0) {
                    result[i][0] = 0;
                } else {
                    if (obstacleGrid[i - 1][0] == 1) {
                        result[i][0] = 0;
                    } else {
                        result[i][0] = 1;
                    }
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                result[0][i] = 0;
            } else {
                if (result[0][i - 1] == 0) {
                    result[0][i] = 0;
                } else {
                    if (obstacleGrid[0][i - 1] == 1) {
                        result[0][i] = 0;
                    } else {
                        result[0][i] = 1;
                    }
                }
            }
        }
        //2,遍历
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //3,动态规则方程
                if (obstacleGrid[i][j] == 1) {
                    result[i][j] = 0;
                } else {
                    if (obstacleGrid[i - 1][j] == 1 && obstacleGrid[i][j - 1] == 1) {
                        result[i][j] = 0;
                    } else if (obstacleGrid[i - 1][j] == 1) {
                        result[i][j] = result[i][j - 1];
                    } else if (obstacleGrid[i][j - 1] == 1) {
                        result[i][j] = result[i - 1][j];
                    } else {
                        result[i][j] = result[i - 1][j] + result[i][j - 1];
                    }
                }
            }
        }
        return result[m - 1][n - 1];
    }

}

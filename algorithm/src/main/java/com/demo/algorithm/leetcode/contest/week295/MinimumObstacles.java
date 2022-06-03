package com.demo.algorithm.leetcode.contest.week295;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by chl on 2022/6/3.
 * description : 到达角落需要移除障碍物的最小数目
 *
 * 给你一个下标从0开始的二维整数数组grid，数组大小为m x n 。每个单元格都是两个值之一：
 * 0 表示一个 空单元格，
 * 1 表示一个可以移除的障碍物 。
 * 你可以向上、下、左、右移动，从一个空单元格移动到另一个空单元格。
 * 现在你需要从左上角(0, 0) 移动到右下角 (m - 1, n - 1) ，返回需要移除的障碍物的最小数目。
 *
 * 示例 1：
 * 输入：grid = [[0,1,1],[1,1,0],[1,1,0]]
 * 输出：2
 * 解释：可以移除位于 (0, 1) 和 (0, 2) 的障碍物来创建从 (0, 0) 到 (2, 2) 的路径。
 * 可以证明我们至少需要移除两个障碍物，所以返回 2 。
 * 注意，可能存在其他方式来移除 2 个障碍物，创建出可行的路径。
 *
 * 示例 2：
 * 输入：grid = [[0,1,0,0,0],[0,1,0,1,0],[0,0,0,1,0]]
 * 输出：0
 * 解释：不移除任何障碍物就能从 (0, 0) 到 (2, 4) ，所以返回 0 。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10^5
 * 2 <= m * n <= 10^5
 * grid[i][j]为0或1
 * grid[0][0] == grid[m - 1][n - 1] == 0
 */
public class MinimumObstacles {

    private static final int[][] OFF_SETS = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //1，记录当前位置时移动的障碍物，默认赋值-1代表未统计，防止可以防止重复计算
        int[][] marks = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                marks[i][j] = -1;
            }
        }
        marks[0][0] = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        stack.add(new int[]{0, 0});
        while (stack.size() > 0) {
            int[] cur = stack.pop();
            for (int j = 0; j < OFF_SETS.length; j++) {
                int nx = cur[0] + OFF_SETS[j][0];
                int ny = cur[1] + OFF_SETS[j][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    //防止偏移越界
                    if (marks[nx][ny] >= 0) {
                        //当前位置之前已经计算过
                        continue;
                    }
                    marks[nx][ny] = marks[cur[0]][cur[1]] + grid[nx][ny];
                    if (nx == m - 1 && ny == n - 1) {
                        return marks[nx][ny];
                    }
                    if (grid[nx][ny] == 0) {
                        stack.addFirst(new int[]{nx, ny});
                    } else {
                        stack.addLast(new int[]{nx, ny});
                    }
                }
            }
        }
        return marks[m - 1][n - 1];
    }
}

package com.demo.algorithm.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by chl on 2022/2/12.
 * description : 飞地的数量
 *
 * 给你一个大小为m x n的二进制矩阵grid，其中0表示一个海洋单元格、1表示一个陆地单元格。
 * 一次移动是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过grid的边界。
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 *
 * 示例 1：
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 *
 * 示例 2：
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 的值为 0 或 1
 */
public class EnclaveNum {

    /**
     * 分别对应：陆地，飞地，不是飞地
     */
    private static final int LAND = 1;
    private static final int ENCLAVE = 2;
    private static final int UN_ENCLAVE = -1;

    private static final int MOD = 501;

    //分别对应左上右下的偏移
    private int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private Set<Integer> marks = new HashSet<>();

    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == LAND) {
                    marks.clear();
                    boolean enclave = isEnclave(i, j, grid);
                    //如果当前单元格为飞地,状态为3，不是飞地状态为-1。此时被标记访问的状态也相同
                    if (enclave) {
                        count += marks.size();
                        for (int num : marks) {
                            grid[num / MOD][num % MOD] = ENCLAVE;
                        }
                    } else {
                        for (int num : marks) {
                            grid[num / MOD][num % MOD] = UN_ENCLAVE;
                        }
                    }
                }
            }
        }
        return count;
    }

    //判断当前单元格(x,y)是否为飞地
    private boolean isEnclave(int x, int y, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //1，判断当前位置是否为边界，边界不能为飞地，直接返回false
        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
            return false;
        }
        marks.add(x * MOD + y);
        for (int i = 0; i < 4; i++) {
            int nx = x + offsets[i][0];
            int ny = y + offsets[i][1];
            if (grid[nx][ny] == ENCLAVE) {
                return true;
            }
            if (grid[nx][ny] == UN_ENCLAVE) {
                return false;
            }
            if (grid[nx][ny] == LAND) {
                if (marks.contains(nx * MOD + ny)) {
                    continue;
                }
                if (!isEnclave(nx, ny, grid)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int numEnclaves2(int[][] grid) {
        /**
         * 从边界进行边界查找并标记不是飞地的陆地
         */
        int m = grid.length;
        int n = grid[0].length;
        if (m <= 2 || n <= 2) {
            return 0;
        }
        boolean[][] marks = new boolean[m][n];
        //遍历搜索第一列与最后一列
        for (int i = 0; i < m; i++) {
            if (grid[i][0] == 1 && !marks[i][0]) {
                dfs(i, 0, grid, marks);
            }
            if (grid[i][n - 1] == 1 && !marks[i][n - 1]) {
                dfs(i, n - 1, grid, marks);
            }
        }
        //遍历搜索第一行与最后一行
        for (int i = 0; i < n; i++) {
            if (grid[0][i] == 1 && !marks[0][i]) {
                dfs(0, i, grid, marks);
            }
            if (grid[m - 1][i] == 1 && !marks[m - 1][i]) {
                dfs(m - 1, i, grid, marks);
            }
        }
        int count = 0;
        //统计陆地飞地的数量
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !marks[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(int x, int y, int[][] grid, boolean[][] marks) {
        marks[x][y] = true;
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < 4; i++) {
            int nx = x + offsets[i][0];
            int ny = y + offsets[i][1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                //防止偏移越界
                if (grid[nx][ny] == 1 && !marks[nx][ny]) {
                    //只处理未标记的
                    dfs(nx, ny, grid, marks);
                }
            }
        }
    }
}

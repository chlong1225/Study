package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/14
 * @author chenglong
 * description : 岛屿数量
 *
 * 给你一个由'1'（陆地）和'0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为'0'或'1'
 */
public class NumIslands {

    private static final int[][] OFFSETS = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visits = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && (!visits[i][j])) {
                    count++;
                    List<int[]> curs = new ArrayList<>();
                    curs.add(new int[]{i, j});
                    visits[i][j] = true;
                    List<int[]> nexts = new ArrayList<>();
                    while (curs.size() > 0) {
                        for (int k = 0; k < curs.size(); k++) {
                            for (int l = 0; l < OFFSETS.length; l++) {
                                int nx = curs.get(k)[0] + OFFSETS[l][0];
                                int ny = curs.get(k)[1] + OFFSETS[l][1];
                                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                                    //防止越界
                                    if (grid[nx][ny] == '1' && (!visits[nx][ny])) {
                                        nexts.add(new int[]{nx, ny});
                                        visits[nx][ny] = true;
                                    }
                                }
                            }
                        }
                        curs.clear();
                        curs.addAll(nexts);
                        nexts.clear();
                    }
                }
            }
        }
        return count;
    }
}

package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/6/19
 * @author chenglong
 * description : 统计封闭岛屿的数目
 *
 * 二维矩阵grid由0（土地）和1（水）组成。岛是由最大的4个方向连通的0组成的群，封闭岛是一个完全由1包围（左、上、右、下）的岛。
 * 请返回封闭岛屿的数目。
 *
 * 示例 1：
 * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * 输出：2
 * 解释：
 * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
 *
 * 示例 2：
 * 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * 输出：1
 *
 * 示例 3：
 * 输入：grid = [[1,1,1,1,1,1,1],
 *             [1,0,0,0,0,0,1],
 *             [1,0,1,1,1,0,1],
 *             [1,0,1,0,1,0,1],
 *             [1,0,1,1,1,0,1],
 *             [1,0,0,0,0,0,1],
 *              [1,1,1,1,1,1,1]]
 * 输出：2
 *
 * 提示：
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 */
public class ClosedIsland {

    private final int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //用于记录当前陆地是否已经被搜索过
        boolean[][] marks = new boolean[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && !marks[i][j]) {
                    List<int[]> points = getPath(i, j, grid, marks);
                    if (checkPath(points, m, n)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private boolean checkPath(List<int[]> points, int m, int n) {
        for (int i = 0; i < points.size(); i++) {
            int x = points.get(i)[0];
            int y = points.get(i)[1];
            //陆地必须不能是周边
            if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
                return false;
            }
        }
        return true;
    }

    private List<int[]> getPath(int startX, int startY, int[][] grid, boolean[][] marks) {
        int m = grid.length;
        int n = grid[0].length;
        List<int[]> points = new ArrayList<>();
        points.add(new int[]{startX, startY});
        marks[startX][startY] = true;
        List<int[]> curs = new ArrayList<>();
        curs.add(new int[]{startX, startY});
        List<int[]> nexts = new ArrayList<>();
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                for (int j = 0; j < offsets.length; j++) {
                    int nx = curs.get(i)[0] + offsets[j][0];
                    int ny = curs.get(i)[1] + offsets[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0 && !marks[nx][ny]) {
                        nexts.add(new int[]{nx, ny});
                        points.add(new int[]{nx, ny});
                        marks[nx][ny] = true;
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return points;
    }
}

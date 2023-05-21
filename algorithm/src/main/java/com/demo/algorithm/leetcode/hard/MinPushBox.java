package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/5/8
 * @author chenglong
 * description : 推箱子
 *
 *「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。
 * 游戏地图用大小为m x n的网格grid表示，其中每个元素可以是墙、地板或者是箱子。
 * 现在你将作为玩家参与游戏，按规则将箱子'B'移动到目标位置'T'：
 * 玩家用字符'S'表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。
 * 地板用字符'.'表示，意味着可以自由行走。
 * 墙用字符'#'表示，意味着障碍物，不能通行。
 * 箱子仅有一个，用字符'B'表示。相应地，网格上有一个目标位置'T'。
 * 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。
 * 玩家无法越过箱子。
 * 返回将箱子推到目标位置的最小推动次数，如果无法做到，请返回-1。
 *
 * 示例 1：
 * 输入：grid = [["#","#","#","#","#","#"],
 *              ["#","T","#","#","#","#"],
 *             ["#",".",".","B",".","#"],
 *             ["#",".","#","#",".","#"],
 *             ["#",".",".",".","S","#"],
 *             ["#","#","#","#","#","#"]]
 * 输出：3
 * 解释：我们只需要返回推箱子的次数。
 *
 * 示例 2：
 * 输入：grid = [["#","#","#","#","#","#"],
 *              ["#","T","#","#","#","#"],
 *             ["#",".",".","B",".","#"],
 *             ["#","#","#","#",".","#"],
 *             ["#",".",".",".","S","#"],
 *             ["#","#","#","#","#","#"]]
 * 输出：-1
 *
 * 示例 3：
 * 输入：grid = [["#","#","#","#","#","#"],
 *             ["#","T",".",".","#","#"],
 *             ["#",".","#","B",".","#"],
 *             ["#",".",".",".",".","#"],
 *             ["#",".",".",".","S","#"],
 *             ["#","#","#","#","#","#"]]
 * 输出：5
 * 解释：向下、向左、向左、向上再向上。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * grid仅包含字符'.', '#', 'S' , 'T', 以及'B'。
 * grid中'S', 'B'和'T'各只能出现一个。
 */
public class MinPushBox {

    private final int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int minPushBox(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //1，遍历查找玩家，箱子，目标的位置
        int sx = -1;
        int sy = -1;
        int bx = -1;
        int by = -1;
        int tx = -1;
        int ty = -1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                } else if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                } else if (grid[i][j] == 'T') {
                    tx = i;
                    ty = j;
                }
            }
        }
        //2，过滤特殊场景
        if (!isCanEnd(tx, ty, grid)) {
            return -1;
        }
        //0,1,2,3分别代表玩家在箱子的左上右下方向。
        int[][][] marks = new int[m][n][4];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    marks[i][j][k] = -1;
                }
            }
        }
        List<int[]> curs = new ArrayList<>();
        List<int[]> nexts = new ArrayList<>();
        int step = 0;
        for (int i = 0; i < 4; i++) {
            if (checkPosition(sx, sy, bx, by, grid, i)) {
                marks[bx][by][i] = step;
                curs.add(new int[]{bx, by, i});
            }
        }
        while (curs.size() > 0) {
            step++;
            for (int i = 0; i < curs.size(); i++) {
                int[] cur = curs.get(i);
                //玩家相对箱子的位置
                int direction = cur[2];
                //玩家的位置
                sx = cur[0] + offsets[direction][0];
                sy = cur[1] + offsets[direction][1];
                //箱子被推到的位置
                int nbx = cur[0] - offsets[direction][0];
                int nby = cur[1] - offsets[direction][1];
                if (nbx >= 0 && nbx < m && nby >= 0 && nby < n) {
                    if (grid[nbx][nby] == '#') {
                        continue;
                    }
                    if (nbx == tx && nby == ty) {
                        return step;
                    }
                    if (marks[nbx][nby][direction] == -1) {
                        marks[nbx][nby][direction] = step;
                        nexts.add(new int[]{nbx, nby, direction});
                    }
                    for (int j = 0; j < 4; j++) {
                        if (j != direction) {
                            if (marks[nbx][nby][j] == -1 && checkPosition(sx, sy, nbx, nby, grid, j)) {
                                marks[nbx][nby][j] = step;
                                nexts.add(new int[]{nbx, nby, j});
                            }
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return -1;
    }

    private boolean checkPosition(int sx, int sy, int bx, int by, char[][] grid, int direction) {
        int m = grid.length;
        int n = grid[0].length;
        //玩家需要到达的指定位置，便于可以推动箱子
        int tx = bx + offsets[direction][0];
        int ty = by + offsets[direction][1];
        if (tx < 0 || tx >= m || ty < 0 || ty >= n) {
            return false;
        }
        if (grid[tx][ty] == '#') {
            return false;
        }
        if (tx == sx && ty == sy) {
            return true;
        }
        boolean[][] marks = new boolean[m][n];
        List<int[]> dates = new ArrayList<>();
        List<int[]> nexts = new ArrayList<>();
        marks[sx][sy] = true;
        dates.add(new int[]{sx, sy});
        while (dates.size() > 0) {
            for (int i = 0; i < dates.size(); i++) {
                //当前位置
                int[] cur = dates.get(i);
                for (int j = 0; j < 4; j++) {
                    int nx = cur[0] + offsets[j][0];
                    int ny = cur[1] + offsets[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        if (nx == tx && ny == ty) {
                            return true;
                        }
                        if (marks[nx][ny]) {
                            continue;
                        }
                        marks[nx][ny] = true;
                        if (nx == bx && ny == by || grid[nx][ny] == '#') {
                            continue;
                        }
                        nexts.add(new int[]{nx, ny});
                    }
                }
            }
            dates.clear();
            dates.addAll(nexts);
            nexts.clear();
        }
        return false;
    }

    //检查目标是否可以到达，即周围是否都是墙
    private boolean isCanEnd(int x, int y, char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        if (x - 1 >= 0 && grid[x - 1][y] != '#') {
            return true;
        }
        if (x + 1 < m && grid[x + 1][y] != '#') {
            return true;
        }
        if (y - 1 >= 0 && grid[x][y - 1] != '#') {
            return true;
        }
        if (y + 1 < n && grid[x][y + 1] != '#') {
            return true;
        }
        return false;
    }
}

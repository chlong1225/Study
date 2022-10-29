package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/10/25
 * @author chenglong
 * description : 最短的桥
 *
 * 给你一个大小为n x n的二元矩阵grid，其中1表示陆地，0表示水域。
 * 岛是由四面相连的1形成的一个最大组，即不会与非组内的任何其他1相连。grid中恰好存在两座岛 。
 * 你可以将任意数量的0变为1，以使两座岛连接起来变成一座岛。
 * 返回必须翻转的0的最小数目。
 *
 * 示例 1：
 * 输入：grid = [[0,1],[1,0]]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 *
 * 示例 3：
 * 输入：grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 *
 * 提示：
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] 为 0 或 1
 * grid 中恰有两个岛
 */
public class ShortestBridge {

    private static final int[][] OFFSETS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        //1，遍历查找第一个岛的数据
        boolean[][] marks = new boolean[n][n];
        List<int[]> dates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dates.size() > 0) {
                break;
            }
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    //查找到第一个岛
                    findFirstBridge(i, j, grid, marks, dates);
                    break ;
                }
            }
        }
        //2，遍历扩散
        int step = 0;
        List<int[]> next = new ArrayList<>();
        while (dates.size() > 0) {
            for (int i = 0; i < dates.size(); i++) {
                int[] points = dates.get(i);
                for (int j = 0; j < OFFSETS.length; j++) {
                    int nx = points[0] + OFFSETS[j][0];
                    int ny = points[1] + OFFSETS[j][1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        if (marks[nx][ny]) {
                            continue;
                        }
                        if (grid[nx][ny] == 1) {
                            return step;
                        }
                        marks[nx][ny] = true;
                        next.add(new int[]{nx, ny});
                    }
                }
            }
            step++;
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return step;
    }

    private void findFirstBridge(int i, int j, int[][] grid, boolean[][] marks, List<int[]> dates) {
        int n = grid.length;
        marks[i][j] = true;
        dates.add(new int[]{i, j});
        List<int[]> cur = new ArrayList<>(dates);
        List<int[]> next = new ArrayList<>();
        while (cur.size() > 0) {
            for (int k = 0; k < cur.size(); k++) {
                int[] points = cur.get(k);
                for (int l = 0; l < OFFSETS.length; l++) {
                    int nx = points[0] + OFFSETS[l][0];
                    int ny = points[1] + OFFSETS[l][1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        //防止偏移越界
                        if (grid[nx][ny] == 1 && !marks[nx][ny]) {
                            marks[nx][ny] = true;
                            int[] tem = new int[]{nx, ny};
                            dates.add(tem);
                            next.add(tem);
                        }
                    }
                }
            }
            cur.clear();
            cur.addAll(next);
            next.clear();
        }
    }
}

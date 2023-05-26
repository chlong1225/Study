package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2023/5/26.
 * description : 二进制矩阵中的最短路径
 *
 * 给你一个n x n的二进制矩阵grid中，返回矩阵中最短畅通路径的长度。如果不存在这样的路径，返回-1。
 * 二进制矩阵中的畅通路径是一条从左上角单元格（即，(0, 0)）到右下角单元格（即，(n-1, n-1)）的路径，该路径同时满足下述要求：
 * 路径途经的所有单元格的值都是0 。
 * 路径中所有相邻的单元格应当在8个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 *
 *
 * 示例 1：
 * 输入：grid = [[0,1],[1,0]]
 * 输出：2
 *
 * 示例 2：
 * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
 * 输出：4
 *
 * 示例 3：
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
 * 输出：-1
 *
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 为 0 或 1
 */
public class ShortestPathBinaryMatrix {

    private final int[][] offsets = {{-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        if (n == 1) {
            return 1;
        }
        //记录当前位置是否被使用过
        boolean[][] marks = new boolean[n][n];
        int step = 1;
        List<int[]> curs = new ArrayList<>();
        List<int[]> nexts = new ArrayList<>();
        curs.add(new int[]{0, 0});
        marks[0][0] = true;
        while (curs.size() > 0) {
            step++;
            for (int i = 0; i < curs.size(); i++) {
                for (int j = 0; j < offsets.length; j++) {
                    int nx = curs.get(i)[0] + offsets[j][0];
                    int ny = curs.get(i)[1] + offsets[j][1];
                    if (nx == n - 1 && ny == n - 1) {
                        return step;
                    }
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        //防止越界
                        if (grid[nx][ny] == 0 && !marks[nx][ny]) {
                            marks[nx][ny] = true;
                            nexts.add(new int[]{nx, ny});
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
}

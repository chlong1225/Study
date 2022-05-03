package com.demo.algorithm.leetcode.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Created by chl on 2022/5/3.
 * description : 太平洋大西洋水流问题
 *
 * 有一个m × n的矩形岛屿，与太平洋和大西洋相邻。“太平洋”处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个m x n的整数矩阵heights，heights[r][c]表示坐标(r, c)上单元格高于海平面的高度 。
 * 岛上雨水较多，如果相邻单元格的高度小于或等于当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * 返回网格坐标result的2D列表，其中result[i]=[ri, ci]表示雨水从单元格(ri, ci)流动 既可流向太平洋也可流向大西洋。
 *
 * 示例 1：
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 *
 * 示例 2：
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 *
 * 提示：
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 10^5
 */
public class PacificAtlantic {

    private int[][] offsets = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        //记录当前位置是否可以留到太平洋
        boolean[][] pacificMarks = new boolean[m][n];
        //1，左边和上方的边界位置都可以流到太平洋
        Deque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            pacificMarks[i][0] = true;
            stack.push(new int[]{i, 0});
        }
        for (int i = 1; i < n; i++) {
            pacificMarks[0][i] = true;
            stack.push(new int[]{0, i});
        }
        while (!stack.isEmpty()) {
            int[] pop = stack.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pop[0] + offsets[i][0];
                int ny = pop[1] + offsets[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    //防止越界
                    if (pacificMarks[nx][ny]) {
                        continue;
                    }
                    if (heights[nx][ny] >= heights[pop[0]][pop[1]]) {
                        pacificMarks[nx][ny] = true;
                        stack.push(new int[]{nx, ny});
                    }
                }
            }
        }
        //2，右边和底部的边界位置可以流到大西洋
        boolean[][] atlanticMarks = new boolean[m][n];
        stack.clear();
        for (int i = 0; i < n; i++) {
            atlanticMarks[m - 1][i] = true;
            stack.push(new int[]{m - 1, i});
        }
        for (int i = 0; i < m - 1; i++) {
            atlanticMarks[i][n - 1] = true;
            stack.push(new int[]{i, n - 1});
        }
        while (!stack.isEmpty()) {
            int[] pop = stack.poll();
            for (int i = 0; i < 4; i++) {
                int nx = pop[0] + offsets[i][0];
                int ny = pop[1] + offsets[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    //防止越界
                    if (atlanticMarks[nx][ny]) {
                        continue;
                    }
                    if (heights[nx][ny] >= heights[pop[0]][pop[1]]) {
                        atlanticMarks[nx][ny] = true;
                        stack.push(new int[]{nx, ny});
                    }
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificMarks[i][j] && atlanticMarks[i][j]) {
                    List<Integer> items = new ArrayList<>();
                    items.add(i);
                    items.add(j);
                    result.add(items);
                }
            }
        }
        return result;
    }
}

package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/11/9
 * @author chenglong
 * description : 逃离火灾
 *
 * 给你一个下标从0开始大小为m x n的二维整数数组grid，它表示一个网格图。每个格子为下面3个值之一：
 * 0 表示草地。
 * 1 表示着火的格子。
 * 2 表示一座墙，你跟火都不能通过这个格子。
 * 一开始你在最左上角的格子(0,0)，你想要到达最右下角的安全屋格子(m-1,n-1)。每一分钟，你可以移动到相邻的草地格子。每次你移动之后，着火的格子会扩散到所有不是墙的相邻格子。
 * 请你返回你在初始位置可以停留的最多分钟数，且停留完这段时间后你还能安全到达安全屋。如果无法实现，请你返回-1。如果不管你在初始位置停留多久，你总是能到达安全屋，请你返回10^9。
 * 注意，如果你到达安全屋后，火马上到了安全屋，这视为你能够安全到达安全屋。
 * 如果两个格子有共同边，那么它们为相邻格子。
 *
 * 示例 1：
 * 输入：grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
 * 输出：3
 * 解释：上图展示了你在初始位置停留3分钟后的情形。
 * 你仍然可以安全到达安全屋。
 * 停留超过3分钟会让你无法安全到达安全屋。
 *
 * 示例 2：
 * 输入：grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
 * 输出：-1
 * 解释：上图展示了你马上开始朝安全屋移动的情形。
 * 火会蔓延到你可以移动的所有格子，所以无法安全到达安全屋。
 * 所以返回-1。
 *
 * 示例 3：
 * 输入：grid = [[0,0,0],[2,2,0],[1,2,0]]
 * 输出：1000000000
 * 解释：上图展示了初始网格图。
 * 注意，由于火被墙围了起来，所以无论如何你都能安全到达安全屋。
 * 所以返回10^9。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 300
 * 4 <= m * n <= 2*10^4
 * grid[i][j]是0，1或者2。
 * grid[0][0]==grid[m-1][n-1]==0
 */
public class MaximumMinutes {

    private static final int[][] OFFSETS = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private static final int MAX = 1000000000;

    public int maximumMinutes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //1，计算火苗到达当前网格的时间
        int[][] times = checkTimes(grid);
        //2，使用二分法判断是否可以到达安全屋
        int result = -1;
        int start = 0;
        int end = MAX;
        while (start <= end) {
            int middle = (end - start) / 2 + start;
            if (checkPath(middle, times, grid)) {
                result = middle;
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }
        return result >= m * n ? MAX : result;
    }

    private boolean checkPath(int time, int[][] dates, int[][] grid) {
        //1，判断起点是否会着火
        if (dates[0][0] > 0 && time >= dates[0][0]) {
            return false;
        }
        int m = grid.length;
        int n = grid[0].length;
        int step = 0;
        boolean[][] marks = new boolean[m][n];
        List<int[]> curs = new ArrayList<>();
        List<int[]> nexts = new ArrayList<>();
        curs.add(new int[]{0, 0});
        marks[0][0] = true;
        while (curs.size() > 0) {
            step++;
            for (int i = 0; i < curs.size(); i++) {
                int[] cur = curs.get(i);
                for (int j = 0; j < OFFSETS.length; j++) {
                    int nx = cur[0] + OFFSETS[j][0];
                    int ny = cur[1] + OFFSETS[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 0 && (!marks[nx][ny])) {
                        //防止越界并且当前位置为草地，并且当前位置之前没有经过
                        //检查是否到达安全屋
                        if (nx == m - 1 && ny == n - 1) {
                            if (dates[nx][ny] < 0 || (step + time <= dates[nx][ny])) {
                                return true;
                            }
                        } else {
                            marks[nx][ny] = true;
                            if (dates[nx][ny] == -1 || step + time < dates[nx][ny]) {
                                //当前位置不会着火，可以访问到达
                                nexts.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return false;
    }

    private int[][] checkTimes(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] times = new int[m][n];
        List<int[]> curs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    times[i][j] = 0;
                    curs.add(new int[]{i, j});
                } else if (grid[i][j] == 0) {
                    times[i][j] = -1;
                } else {
                    //当前位置为墙壁
                    times[i][j] = -2;
                }
            }
        }
        List<int[]> nexts = new ArrayList<>();
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                int[] cur = curs.get(i);
                for (int j = 0; j < OFFSETS.length; j++) {
                    int nx = cur[0] + OFFSETS[j][0];
                    int ny = cur[1] + OFFSETS[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        if (times[nx][ny] == -1) {
                            times[nx][ny] = times[cur[0]][cur[1]] + 1;
                            nexts.add(new int[]{nx, ny});
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return times;
    }
}

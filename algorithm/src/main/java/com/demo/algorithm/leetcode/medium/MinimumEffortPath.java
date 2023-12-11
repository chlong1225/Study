package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/11
 * @author chenglong
 * description : 最小体力消耗路径
 *
 * 你准备参加一场远足活动。给你一个二维rows x columns的地图heights，其中heights[row][col]表示格子(row,col)的高度。
 * 一开始你在最左上角的格子(0,0)，且你希望去最右下角的格子(rows-1, columns-1)（注意下标从0开始编号）。
 * 你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费体力最小的一条路径。
 * 一条路径耗费的体力值是路径上相邻格子之间高度差绝对值的最大值决定的。
 * 请你返回从左上角走到右下角的最小体力消耗值。
 *
 * 示例 1：
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为3。
 *
 * 示例 2：
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 *
 * 示例 3：
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 *
 * 提示：
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 10^6
 */
public class MinimumEffortPath {

    //分别对应左上右下四个方向的偏移
    private static final int[][] OFFSETS = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public int minimumEffortPath(int[][] heights) {
        //处理特殊场景
        int m = heights.length;
        int n = heights[0].length;
        if (m * n == 1) {
            return 0;
        }
        int min = 0;
        int max = 1000000;
        while (min < max) {
            int middle = (max - min) / 2 + min;
            if (isArrive(middle, heights)) {
                max = middle;
            } else {
                min = middle + 1;
            }
        }
        return min;
    }

    private boolean isArrive(int compare, int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<int[]> curs = new ArrayList<>();
        List<int[]> nexts = new ArrayList<>();
        boolean[][] visits = new boolean[m][n];
        visits[0][0] = true;
        curs.add(new int[]{0, 0});
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                int x = curs.get(i)[0];
                int y = curs.get(i)[1];
                for (int j = 0; j < OFFSETS.length; j++) {
                    int nx = x + OFFSETS[j][0];
                    int ny = y + OFFSETS[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        //防止越界
                        if (!visits[nx][ny]) {
                            //防止路径重复
                            int diff = Math.abs(heights[nx][ny] - heights[x][y]);
                            if (diff <= compare) {
                                if (nx == m - 1 && ny == n - 1) {
                                    return true;
                                }
                                visits[nx][ny] = true;
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
}

package com.demo.algorithm.leetcode.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/3/27.
 * description : 剑指Offer13. 机器人的运动范围
 *
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标[0,0]的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 示例 1：
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 *
 * 示例 2：
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 *
 * 提示：
 * 1 <= n,m <= 100
 * 0 <= k<= 20
 */
public class MovingCount {

    private int[][] offsets = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int movingCount(int m, int n, int k) {
        if (k == 0) {
            return 1;
        }
        boolean[][] marks = new boolean[m][n];
        int count = 1;
        List<int[]> dates = new ArrayList<>();
        List<int[]> next = new ArrayList<>();
        dates.add(new int[]{0, 0});
        marks[0][0] = true;
        while (dates.size() > 0) {
            for (int i = 0; i < dates.size(); i++) {
                int[] points = dates.get(i);
                for (int j = 0; j < offsets.length; j++) {
                    int nx = points[0] + offsets[j][0];
                    int ny = points[1] + offsets[j][1];
                    if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        //防止越界
                        if (!marks[nx][ny]) {
                            //防止重复进入
                            int sum = (nx / 10) + (nx % 10) + (ny / 10) + (ny % 10);
                            if (sum <= k) {
                                count++;
                                marks[nx][ny] = true;
                                next.add(new int[]{nx, ny});
                            }
                        }
                    }
                }
            }
            dates.clear();
            dates.addAll(next);
            next.clear();
        }
        return count;
    }
}

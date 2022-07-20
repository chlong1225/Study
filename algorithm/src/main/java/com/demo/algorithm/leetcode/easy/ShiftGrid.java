package com.demo.algorithm.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2022/7/20
 * @author chenglong
 * description : 二维网格迁移
 *
 * 给你一个m行n列的二维网格grid和一个整数k。你需要将grid迁移k次。
 * 每次「迁移」操作将会引发下述活动：
 * 位于 grid[i][j]的元素将会移动到grid[i][j + 1]。
 * 位于grid[i][n- 1] 的元素将会移动到grid[i + 1][0]。
 * 位于 grid[m- 1][n - 1]的元素将会移动到grid[0][0]。
 * 请你返回k 次迁移操作后最终得到的 二维网格。
 *
 * 示例 1：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * 输出：[[9,1,2],[3,4,5],[6,7,8]]
 *
 * 示例 2：
 * 输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * 输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 *
 * 示例 3：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * 输出：[[1,2,3],[4,5,6],[7,8,9]]
 *
 * 提示：
 * m ==grid.length
 * n ==grid[i].length
 * 1 <= m <= 50
 * 1 <= n <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 */
public class ShiftGrid {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dates = new int[m][n];
        int count = m * n;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = (i * n + j + k) % count;
                dates[index / n][index % n] = grid[i][j];
            }
        }
        List<List<Integer>> result = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            List<Integer> item = new ArrayList<>(n);
            for (int j = 0; j < n; j++) {
                item.add(dates[i][j]);
            }
            result.add(item);
        }
        return result;
    }
}

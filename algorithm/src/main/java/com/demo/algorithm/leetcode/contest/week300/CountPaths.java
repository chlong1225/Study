package com.demo.algorithm.leetcode.contest.week300;

/**
 * Created by chl on 2022/7/3.
 * description : 网格图中递增路径的数目
 *
 * 给你一个m x n的整数网格图grid，你可以从一个格子移动到4个方向相邻的任意一个格子。
 * 请你返回在网格图中从任意格子出发，达到任意格子，且路径中的数字是严格递增的路径数目。由于答案可能会很大，请将结果对10^9 + 7取余后返回。
 * 如果两条路径中访问过的格子不是完全相同的，那么它们视为两条不同的路径。
 *
 * 示例 1：
 * 输入：grid = [[1,1],[3,4]]
 * 输出：8
 * 解释：严格递增路径包括：
 * - 长度为 1 的路径：[1]，[1]，[3]，[4] 。
 * - 长度为 2 的路径：[1 -> 3]，[1 -> 4]，[3 -> 4] 。
 * - 长度为 3 的路径：[1 -> 3 -> 4] 。
 * 路径数目为 4 + 3 + 1 = 8 。
 *
 * 示例 2：
 * 输入：grid = [[1],[2]]
 * 输出：3
 * 解释：严格递增路径包括：
 * - 长度为 1 的路径：[1]，[2] 。
 * - 长度为 2 的路径：[1 -> 2] 。
 * 路径数目为 2 + 1 = 3 。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 1000
 * 1 <= m * n <= 10^5
 * 1 <= grid[i][j] <= 10^5
 */
public class CountPaths {

   private static final int MOD = 1000000007;
   private int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

   public int countPaths(int[][] grid) {
      int m = grid.length;
      int n = grid[0].length;
      int[][] marks = new int[m][n];
      for (int i = 0; i < m; i++) {
         for (int j = 0; j < n; j++) {
            if (marks[i][j] == 0) {
               dfs(i, j, grid, marks);
            }
         }
      }
      int total = 0;
      for (int i = 0; i < m; i++) {
         for (int j = 0; j < n; j++) {
            total += marks[i][j];
            total %= MOD;
         }
      }
      return total;
   }

   private int dfs(int i, int j, int[][] grid, int[][] marks) {
      if (marks[i][j] != 0) {
         return marks[i][j];
      }
      int m = grid.length;
      int n = grid[0].length;
      int total = 1;
      for (int k = 0; k < offsets.length; k++) {
         int nx = i + offsets[k][0];
         int ny = j + offsets[k][1];
         if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
            //防止越界
            if (grid[i][j] > grid[nx][ny]) {
               total += dfs(nx, ny, grid, marks);
               total %= MOD;
            }
         }
      }
      marks[i][j] = total;
      return total;
   }
}

package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/9/13
 * @author chenglong
 * description : 检查骑士巡视方案
 *
 * 骑士在一张n x n的棋盘上巡视。在有效的巡视方案中，骑士会从棋盘的左上角出发，并且访问棋盘上的每个格子恰好一次。
 * 给你一个n x n的整数矩阵grid，由范围[0, n*n-1]内的不同整数组成，其中grid[row][col]表示单元格(row,col)是骑士访问的第grid[row][col]个单元格。
 * 骑士的行动是从下标0开始的。
 * 如果grid表示了骑士的有效巡视方案，返回true；否则返回false。
 * 注意，骑士行动时可以垂直移动两个格子且水平移动一个格子，或水平移动两个格子且垂直移动一个格子。下图展示了骑士从某个格子出发可能的八种行动路线。
 *
 * 示例 1：
 * 输入：grid = [[0,11,16,5,20],[17,4,19,10,15],[12,1,8,21,6],[3,18,23,14,9],[24,13,2,7,22]]
 * 输出：true
 * 解释：grid 如上图所示，可以证明这是一个有效的巡视方案。
 *
 * 示例 2：
 * 输入：grid = [[0,3,6],[5,8,1],[2,7,4]]
 * 输出：false
 * 解释：grid 如上图所示，考虑到骑士第 7 次行动后的位置，第 8 次行动是无效的。
 *
 * 提示：
 * n == grid.length == grid[i].length
 * 3 <= n <= 7
 * 0 <= grid[row][col] < n * n
 * grid 中的所有整数互不相同
 */
public class CheckValidGrid {

    private static final int[][] offsets = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    public boolean checkValidGrid(int[][] grid) {
        if (grid[0][0] != 0) {
            return false;
        }
        int n = grid.length;
        int length = n * n;
        //1，统计整数与位置的相对关系
        int[][] dates = new int[length][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dates[grid[i][j]] = new int[]{i, j};
            }
        }
        //2，依次检查位置是否可以到达
        for (int i = 1; i < length; i++) {
            int[] pre = dates[i - 1];
            int[] cur = dates[i];
            if (!checkJump(pre, cur)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkJump(int[] cur, int[] next) {
        for (int i = 0; i < offsets.length; i++) {
            int nx = cur[0] + offsets[i][0];
            int ny = cur[1] + offsets[i][1];
            if (nx == next[0] && ny == next[1]) {
                return true;
            }
        }
        return false;
    }
}

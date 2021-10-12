package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/10/12.
 * description : N皇后II
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量。
 *
 * 示例 1：
 * 输入：n = 4
 *
 * 输出：2
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *  
 * 提示：
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class NQueen2 {

    private int count;

    public int totalNQueens(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 0;
        }
        count = 0;
        int[][] queens = new int[n][n];
        findQueen(n, 0, queens);
        return count;
    }

    private void findQueen(int n, int index, int[][] queens) {
        if (index == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (checkAvailable(index, i, n, queens)) {
                queens[index][i] = 1;
                findQueen(n, index + 1, queens);
                queens[index][i] = 0;
            }
        }
    }

    private boolean checkAvailable(int i, int j, int n, int[][] queens) {
        //1,检查行,列
        for (int k = 0; k < n; k++) {
            if (queens[i][k] == 1) {
                return false;
            }
            if (queens[k][j] == 1) {
                return false;
            }
        }
        //2,检查斜边
        for (int k = 0; k < i; k++) {
            int left = j - (i - k);
            int right = j + (i - k);
            if (left >= 0 && queens[k][left] == 1) {
                return false;
            }
            if (right < n && queens[k][right] == 1) {
                return false;
            }
        }
        for (int k = i + 1; k < n; k++) {
            int left = j - (k - i);
            int right = j + (k - i);
            if (left >= 0 && queens[k][left] == 1) {
                return false;
            }
            if (right < n && queens[k][right] == 1) {
                return false;
            }
        }
        return true;
    }

}

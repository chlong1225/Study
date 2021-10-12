package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/10/12.
 * description : N皇后
 * <p>
 * n皇后问题研究的是如何将n个皇后放置在n×n的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * <p>
 * 示例 1：
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 * <p>
 * 示例 2：
 * 输入：n = 1
 * 输出：[["Q"]]
 *  
 * 提示：
 * 1 <= n <= 9
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 */
public class NQueen {

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) {
            return null;
        }
        List<List<String>> result = new ArrayList<>();
        if (n == 1) {
            List<String> data = new ArrayList<>();
            data.add("Q");
            result.add(data);
            return result;
        }
        if (n == 2) {
            return result;
        }
        int[][] queens = new int[n][n];
        findQueen(n, 0, queens, result);
        return result;
    }

    private void findQueen(int n, int index, int[][] queens, List<List<String>> result) {
        if (index == n) {
            List<String> data = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    if (queens[i][j] == 1) {
                        builder.append("Q");
                    } else {
                        builder.append(".");
                    }
                }
                data.add(builder.toString());
            }
            result.add(data);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (checkAvailable(index, i, n, queens)) {
                queens[index][i] = 1;
                findQueen(n, index + 1, queens, result);
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

package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/12/18.
 * description : 甲板上的战舰
 *
 * 给你一个大小为m x n的矩阵board表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的战舰的数量。
 * 战舰只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，
 * 其中 k 可以是任意大小。两艘战舰之间至少有一个水平或垂直的空位分隔（即没有相邻的战舰）。
 *  
 * 示例 1：
 * 输入：board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
 * 输出：2
 *
 * 示例 2：
 * 输入：board = [["."]]
 * 输出：0
 *  
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] 是 '.' 或 'X'
 *  
 * 进阶：你可以实现一次扫描算法，并只使用 O(1) 额外空间，并且不修改 board 的值来解决这个问题吗？
 */
public class BattleshipNum {

    public int countBattleships(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            int rowCount = 0;
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    rowCount++;
                } else {
                    if (rowCount == 1) {
                        if (i == 0) {
                            count++;
                        } else {
                            if (board[i - 1][j - 1] != 'X') {
                                count++;
                            }
                        }
                        rowCount = 0;
                    } else if (rowCount > 1) {
                        count++;
                        rowCount = 0;
                    }
                }
            }
            if (rowCount == 1) {
                if (i == 0) {
                    count++;
                } else {
                    if (board[i - 1][n - 1] != 'X') {
                        count++;
                    }
                }
            } else if (rowCount > 1) {
                count++;
            }
        }
        return count;
    }
}

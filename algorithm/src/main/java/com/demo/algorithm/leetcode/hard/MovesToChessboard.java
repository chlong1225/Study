package com.demo.algorithm.leetcode.hard;

/**
 * create on 2022/8/23
 * @author chenglong
 * description : 变为棋盘
 *
 * 一个n x n的二维网络board仅由0和1组成。每次移动你能任意交换两列或是两行的位置。
 * 返回将这个矩阵变为“棋盘”所需的最小移动次数。如果不存在可行的变换，输出 -1。
 * “棋盘” 是指任意一格的上下左右四个方向的值均与本身不同的矩阵。
 *
 * 示例 1:
 * 输入: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
 * 输出: 2
 * 解释:一种可行的变换方式如下，从左到右：
 * 第一次移动交换了第一列和第二列。
 * 第二次移动交换了第二行和第三行。
 *
 * 示例 2:
 * 输入: board = [[0, 1], [1, 0]]
 * 输出: 0
 * 解释: 注意左上角的格值为0时也是合法的棋盘，也是合法的棋盘.
 *
 * 示例 3:
 * 输入: board = [[1, 0], [1, 0]]
 * 输出: -1
 * 解释: 任意的变换都不能使这个输入变为合法的棋盘。
 *
 * 提示：
 * n == board.length
 * n == board[i].length
 * 2 <= n <= 30
 * board[i][j]将只包含0或1
 */
public class MovesToChessboard {

    public int movesToChessboard(int[][] board) {
        //1，检查是否可能合法
        int n = board.length;
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    count++;
                }
            }
            if (n % 2 == 0) {
                if (count != n / 2) {
                    return -1;
                }
            } else {
                if (count != n / 2 && count != (n / 2 + 1)) {
                    return -1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (board[j][i] == 1) {
                    count++;
                }
            }
            if (n % 2 == 0) {
                if (count != n / 2) {
                    return -1;
                }
            } else {
                if (count != n / 2 && count != (n / 2 + 1)) {
                    return -1;
                }
            }
        }
        //2，交换

        return -1;
    }
}

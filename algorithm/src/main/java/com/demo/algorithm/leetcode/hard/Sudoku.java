package com.demo.algorithm.leetcode.hard;

/**
 * Created by chl on 2021/10/10.
 * description : 解数独
 *
 * 编写一个程序，通过填充空格来解决数独问题。
 * 数独的解法需 遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 示例
 * 输入：board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * 输出：[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * 解释：输入的数独如上图所示，唯一有效的解决方案如下所示：
 *
 * 提示：
 * board.length == 9
 * board[i].length == 9
 * board[i][j] 是一位数字或者 '.'
 * 题目数据 保证 输入数独仅有一个解
 */
public class Sudoku {

    boolean isOver = false;

    public void solveSudoku(char[][] board) {
        filling(board,0,0);
    }

    private void filling(char[][] board, int row, int column) {
        if (column == 9) {
            row++;
            column=0;
            if (row >= 9) {
                isOver = true;
                return;
            }
        }
        if (board[row][column] == '.') {
            for (char k = '1'; k <= '9'; k++) {
                if (isOver) {
                    return;
                }
                if (isValib(board, row, column, k)) {
                    board[row][column] = k;
                    filling(board, row, column + 1);
                    if (!isOver) {
                        board[row][column] = '.';
                    }
                }
            }
        } else {
            filling(board, row, column + 1);
        }
    }

    //当前位置{i,j}填充number是否合适
    private boolean isValib(char[][] board, int i, int j, char number) {
        int a = i/3;
        int b = j/3;
        for (int k = 0; k < 9; k++) {
            if (board[i][k] == number) {
                return false;
            }
            if (board[k][j] == number) {
                return false;
            }
            if (board[a * 3 + k / 3][b * 3 + k % 3] == number) {
                return false;
            }
        }
        return true;
    }

}

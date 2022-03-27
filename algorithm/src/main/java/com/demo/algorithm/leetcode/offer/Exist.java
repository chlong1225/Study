package com.demo.algorithm.leetcode.offer;

/**
 * Created by chl on 2022/3/27.
 * description :  剑指Offer12. 矩阵中的路径
 *
 * 给定一个mxn二维字符网格board和一个字符串单词word 。如果word 存在于网格中，返回true ；否则返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 * 输入：board = [["a","b"],["c","d"]], word = "abcd"
 * 输出：false
 *
 * 提示：
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * board 和 word 仅由大小写英文字母组成
 */
public class Exist {

    //位置的偏移
    private int[][] offsets = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        if (m * n < word.length()) {
            return false;
        }
        boolean[][] marks = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == word.charAt(0)) {
                    //开始搜索
                    marks[i][j] = true;
                    if (findWord(i, j, board, 1, word, marks)) {
                        return true;
                    }
                    marks[i][j] = false;
                }
            }
        }
        return false;

    }

    private boolean findWord(int x, int y, char[][] board, int count, String word, boolean[][] marks) {
        int length = word.length();
        if (count == length) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < offsets.length; i++) {
            int nx = x + offsets[i][0];
            int ny = y + offsets[i][1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                //防止越界
                if (!marks[nx][ny] && board[nx][ny] == word.charAt(count)) {
                    marks[nx][ny] = true;
                    if (findWord(nx, ny, board, count + 1, word, marks)) {
                        return true;
                    }
                    marks[nx][ny] = false;
                }
            }
        }
        return false;
    }
}

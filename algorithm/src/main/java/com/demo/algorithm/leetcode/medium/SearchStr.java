package com.demo.algorithm.leetcode.medium;

/**
 * Created by chl on 2021/11/6.
 * description : 单词搜索
 *
 * 给定一个 m x n 二维字符网格board和一个字符串单词word 。如果word存在于网格中，返回true ；否则，返回false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 *
 * 示例 3：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *  
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成 
 *
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */
public class SearchStr {

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        //1,检查word中的单词是否都能在board中找到
        int[] counts = new int[58];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = board[i][j] - 'A';
                counts[index]++;
            }
        }
        int length = word.length();
        for (int i = 0; i < length; i++) {
            int index = word.charAt(i) - 'A';
            if (counts[index] > 0) {
                counts[index]--;
            } else {
                return false;
            }
        }
        if (length == 1) {
            return true;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char start = board[i][j];
                if (start == word.charAt(0)) {
                    //用于记录已经被使用的字符
                    boolean[][] marks = new boolean[m][n];
                    marks[i][j] = true;
                    boolean result = findWord(i, j, 1, marks, board, word);
                    if (result) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //以[i,j]为起点查找word单词
    private boolean findWord(int i, int j, int index, boolean[][] marks, char[][] board, String word) {
        int length = word.length();
        if (index == length) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        //分别对应左上右下
        int[][] positions = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        for (int k = 0; k < 4; k++) {
            int nextX = i + positions[k][0];
            int nextY = j + positions[k][1];
            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !marks[nextX][nextY]) {
                if (board[nextX][nextY] == word.charAt(index)) {
                    marks[nextX][nextY] = true;
                    boolean result = findWord(nextX, nextY, index + 1, marks, board, word);
                    if (result) {
                        return true;
                    }
                    marks[nextX][nextY] = false;
                }
            }
        }
        return false;
    }
}

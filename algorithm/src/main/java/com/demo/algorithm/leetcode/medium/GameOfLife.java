package com.demo.algorithm.leetcode.medium;

/**
 * create on 2023/12/15
 * @author chenglong
 * description : 生命游戏
 *
 * 根据百度百科，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在1970年发明的细胞自动机。
 * 给定一个包含m × n个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1即为活细胞（live），或0即为死细胞（dead）。
 * 每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你m x n网格面板board的当前状态，返回下一个状态。
 *
 * 示例 1：
 * 输入：board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]
 * 输出：[[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
 *
 * 示例 2：
 * 输入：board = [[1,1],[1,0]]
 * 输出：[[1,1],[1,1]]
 *
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 25
 * board[i][j]为0或1
 *
 * 进阶：
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 */
public class GameOfLife {

    private static final int[][] OFFSETS = {{-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};

    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1) {
                    //当前位置为活细胞，统计周围活细胞的数量
                    int count = getCount(i, j, board);
                    if (count == 2 || count == 3) {
                        board[i][j] = 3;
                    }
                } else {
                    //当前位置为死细胞，统计周围活细胞的数量
                    int count = getCount(i, j, board);
                    if (count == 3) {
                        //细胞复合
                        board[i][j] = 2;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] / 2;
            }
        }
    }

    private int getCount(int x, int y, int[][] board) {
        int count = 0;
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < OFFSETS.length; i++) {
            int nx = x + OFFSETS[i][0];
            int ny = y + OFFSETS[i][1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (board[nx][ny] % 2 == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}

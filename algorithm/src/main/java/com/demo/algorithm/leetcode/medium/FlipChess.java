package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/6/21
 * @author chenglong
 * description : 黑白翻转棋
 *
 * 在n*m大小的棋盘中，有黑白两种棋子，黑棋记作字母"X", 白棋记作字母"O"，空余位置记作"."。
 * 当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。
 * 「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 chessboard。若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。
 *
 * 注意：
 * 若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以继续翻转白棋
 * 输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
 *
 * 示例 1：
 * 输入：chessboard = ["....X.","....X.","XOOO..","......","......"]
 * 输出：3
 * 解释：
 * 可以选择下在 [2,4] 处，能够翻转白方三枚棋子。
 *
 * 示例 2：
 * 输入：chessboard = [".X.",".O.","XO."]
 * 输出：2
 * 解释：
 * 可以选择下在 [2,2] 处，能够翻转白方两枚棋子。
 *
 * 示例 3：
 * 输入：chessboard = [".......",".......",".......","X......",".O.....","..O....","....OOX"]
 * 输出：4
 * 解释：
 * 可以选择下在 [6,3] 处，能够翻转白方四枚棋子。
 *
 * 提示：
 * 1 <= chessboard.length, chessboard[i].length <= 8
 * chessboard[i] 仅包含 "."、"O" 和 "X"
 */
public class FlipChess {

    private static final char WHITE = 'O';
    private static final char BLACK = 'X';
    private static final char EMPTY = '.';

    private final int[][] offsets = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};

    public int flipChess(String[] chessboard) {
        int m = chessboard.length;
        int n = chessboard[0].length();
        //统计白棋的数量，即最多可以翻转的个数
        int sum = 0;
        //记录可能有效的放置位置
        boolean[][] valids = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (chessboard[i].charAt(j) == WHITE) {
                    sum++;
                    for (int k = 0; k < offsets.length; k++) {
                        int nx = i + offsets[k][0];
                        int ny = j + offsets[k][1];
                        if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                            //防止越界
                            if (chessboard[nx].charAt(ny) == EMPTY) {
                                valids[nx][ny] = true;
                            }
                        }
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (valids[i][j]) {
                    int count = getFlipCount(i, j, chessboard);
                    if (count > max) {
                        max = count;
                        if (max == sum) {
                            return max;
                        }
                    }
                }
            }
        }
        return max;
    }

    private int getFlipCount(int startX, int startY, String[] chessboard) {
        int m = chessboard.length;
        int n = chessboard[0].length();
        //用于记录可以翻转的位置，防止重复翻转
        boolean[][] marks = new boolean[m][n];
        int count = 0;
        List<int[]> curs = new ArrayList<>();
        List<int[]> nexts = new ArrayList<>();
        curs.add(new int[]{startX, startY});
        marks[startX][startY] = true;
        while (curs.size() > 0) {
            for (int i = 0; i < curs.size(); i++) {
                int x = curs.get(i)[0];
                int y = curs.get(i)[1];
                //8个方向搜索
                for (int j = 0; j < offsets.length; j++) {
                    int tem = 0;
                    int nx = x + offsets[j][0];
                    int ny = y + offsets[j][1];
                    while (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                        if (chessboard[nx].charAt(ny) == WHITE && !marks[nx][ny]) {
                            tem++;
                            nx += offsets[j][0];
                            ny += offsets[j][1];
                        } else {
                            break;
                        }
                    }
                    if (tem > 0) {
                        int endX = x + (tem + 1) * offsets[j][0];
                        int endY = y + (tem + 1) * offsets[j][1];
                        if (endX >= 0 && endX < m && endY >= 0 && endY < n) {
                            if (chessboard[endX].charAt(endY) == BLACK || marks[endX][endY]) {
                                //此时tem个白棋可以翻转
                                if (!marks[endX][endY]) {
                                    marks[endX][endY] = true;
                                    nexts.add(new int[]{endX, endY});
                                }
                                for (int k = 1; k <= tem; k++) {
                                    nx = x + k * offsets[j][0];
                                    ny = y + k * offsets[j][1];
                                    if (!marks[nx][ny]) {
                                        marks[nx][ny] = true;
                                        count++;
                                        nexts.add(new int[]{nx, ny});
                                    }
                                }
                            }
                        }
                    }
                }
            }
            curs.clear();
            curs.addAll(nexts);
            nexts.clear();
        }
        return count;
    }
}

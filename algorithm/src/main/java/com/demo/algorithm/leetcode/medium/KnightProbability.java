package com.demo.algorithm.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * create on 2022/2/17
 * @author chenglong
 * description : 骑士在棋盘上的概率
 *
 * 在一个n x n的国际象棋棋盘上，一个骑士从单元格(row, column)开始，并尝试进行k次移动。行和列是从0开始的，所以左上单元格是(0,0) ，右下单元格是(n-1, n-1) 。
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * 骑士继续移动，直到它走了k步或离开了棋盘。
 * 返回骑士在棋盘停止移动后仍留在棋盘上的概率
 *
 * 示例 1：
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 *
 * 示例 2：
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 *
 * 提示:
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n
 */
public class KnightProbability {

    private static final int MOD = 26;

    /**
     * 分别对应8个方向的偏移量
     */
    private int[][] offsets = {{-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};

    public double knightProbability(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        //记录执行操作后留在棋盘的位置. key = row*26+column ; value = 相同步数中出现的次数
        Map<Integer, Double> positions = new HashMap<>();
        positions.put(row * MOD + column, 1d);
        Map<Integer, Double> next = new HashMap<>();
        for (int step = 1; step <= k; step++) {
            //1,遍历positions
            for (int position : positions.keySet()) {
                for (int i = 0; i < 8; i++) {
                    int nx = position / MOD + offsets[i][0];
                    int ny = position % MOD + offsets[i][1];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        //2,移动后还在棋盘上
                        int tem = nx * MOD + ny;
                        if (next.get(tem) == null) {
                            next.put(tem, positions.get(position) / 8);
                        } else {
                            next.put(tem, next.get(tem) + positions.get(position) / 8);
                        }
                    }
                }
            }
            //数据源重置
            positions.clear();
            positions.putAll(next);
            next.clear();
            if (positions.isEmpty()) {
                break;
            }
        }
        double sum = 0;
        if (positions.isEmpty()) {
            return 0;
        }
        for (double count : positions.values()) {
            sum += count;
        }
        return sum;
    }

    //使用深度dfs遍历+状态记忆
    public double knightProbability2(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        double[][][] marks = new double[n][n][k + 1];
        return dfs(row, column, n, k, marks);
    }

    private double dfs(int x, int y, int n, int k, double[][][] marks) {
        if (x < 0 || x >= n || y < 0 || y >= n) {
            //骑士已经离开棋盘
            return 0;
        }
        if (k == 0) {
            //最后一步还在棋盘上,当前概率为1
            return 1;
        }
        if (marks[x][y][k] != 0) {
            return marks[x][y][k];
        }
        double sum = 0;
        for (int i = 0; i < 8; i++) {
            int nx = x + offsets[i][0];
            int ny = y + offsets[i][1];
            sum += dfs(nx, ny, n, k - 1, marks) / 8.0;
        }
        marks[x][y][k] = sum;
        return sum;
    }

    //使用动态规划
    public double knightProbability3(int n, int k, int row, int column) {
        if (k == 0) {
            return 1;
        }
        /**
         * 由于下一步骑士的路径与上一步相关,故可以使用动态规划进行状态转移
         * marks[row][column][k]: 当前位置(row,column)移动k次后留在棋盘的概率
         * 初始条件: marks[row][column] = 1
         */
        double[][][] marks = new double[n][n][k + 1];
        //1,初始化条件
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                marks[i][j][0] = 1;
            }
        }
        //2,遍历次数
        for (int step = 1; step <= k; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int l = 0; l < 8; l++) {
                        int prex = i - offsets[l][0];
                        int prey = j - offsets[l][1];
                        if (prex >= 0 && prex < n && prey >= 0 && prey < n) {
                            //转移到当前的点之前一个点在棋盘上才有效
                            marks[i][j][step] += marks[prex][prey][step - 1] / 8;
                        }
                    }
                }
            }
        }
        return marks[row][column][k];
    }
}

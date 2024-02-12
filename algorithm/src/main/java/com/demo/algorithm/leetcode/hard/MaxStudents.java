package com.demo.algorithm.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/12/26
 * @author chenglong
 * description : 参加考试的最大学生数
 *
 * 给你一个m * n的矩阵seats表示教室中的座位分布。如果座位是坏的（不可用），就用'#'表示；否则，用'.'表示。
 * 学生可以看到左侧、右侧、左上、右上这四个方向上紧邻他的学生的答卷，但是看不到直接坐在他前面或者后面的学生的答卷。
 * 请你计算并返回该考场可以容纳的同时参加考试且无法作弊的最大学生人数。
 * 学生必须坐在状况良好的座位上。
 *
 * 示例 1：
 * 输入：seats = [["#",".","#","#",".","#"],
 *               [".","#","#","#","#","."],
 *               ["#",".","#","#",".","#"]]
 * 输出：4
 * 解释：教师可以让 4 个学生坐在可用的座位上，这样他们就无法在考试中作弊。
 *
 * 示例 2：
 * 输入：seats = [[".","#"],
 *               ["#","#"],
 *               ["#","."],
 *               ["#","#"],
 *               [".","#"]]
 * 输出：3
 * 解释：让所有学生坐在可用的座位上。
 *
 * 示例 3：
 * 输入：seats = [["#",".",".",".","#"],
 *               [".","#",".","#","."],
 *               [".",".","#",".","."],
 *               [".","#",".","#","."],
 *               ["#",".",".",".","#"]]
 * 输出：10
 * 解释：让学生坐在第 1、3 和 5 列的可用座位上。
 *
 * 提示：
 * seats 只包含字符 '.' 和'#'
 * m == seats.length
 * n == seats[i].length
 * 1 <= m <= 8
 * 1 <= n <= 8
 */
public class MaxStudents {

    //分别对应左侧，右侧，左上，右上的偏移量
    private static final int[][] OFFSETS = {{0, -1}, {0, 1}, {-1, -1}, {-1, 1}};
    private static final char SEAT = '.';

    private int m;
    private int n;
    private int max = 0;
    List<int[]> dates = new ArrayList<>();

    public int maxStudents(char[][] seats) {
        m = seats.length;
        n = seats[0].length;
        //1，统计有效的座位数
        dates.clear();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (seats[i][j] == SEAT) {
                    int[] points = {i, j};
                    dates.add(points);
                }
            }
        }
        if (dates.size() < 2) {
            return dates.size();
        }
        max = 0;
        //2，使用深度搜索+回溯
        boolean[][] marks = new boolean[m][n];
        dfs(0, 0, marks, seats);
        return max;
    }

    private void dfs(int index, int count, boolean[][] marks, char[][] seats) {
        if (index == dates.size()) {
            if (count > max) {
                max = count;
            }
            return;
        }
        //可能最大的数量都小于max，此时裁剪
        if (count + dates.size() - index <= max) {
            return;
        }
        //当前搜索到的位置，判断位置是否有效
        int[] cur = dates.get(index);
        boolean isAble = true;
        for (int i = 0; i < OFFSETS.length; i++) {
            int nx = cur[0] + OFFSETS[i][0];
            int ny = cur[1] + OFFSETS[i][1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                if (seats[nx][ny] == SEAT) {
                    if (marks[nx][ny]) {
                        isAble = false;
                        break;
                    }
                }
            }
        }
        if (isAble) {
            marks[cur[0]][cur[1]] = true;
            dfs(index + 1, count + 1, marks, seats);
            marks[cur[0]][cur[1]] = false;
        }
        dfs(index + 1, count, marks, seats);
    }
}

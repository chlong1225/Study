package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * create on 2023/9/14
 * @author chenglong
 * description : 可以攻击国王的皇后
 *
 * 在一个8x8的棋盘上，放置着若干「黑皇后」和一个「白国王」。
 * 给定一个由整数坐标组成的数组queens ，表示黑皇后的位置；以及一对坐标 king ，表示白国王的位置，返回所有可以攻击国王的皇后的坐标(任意顺序)。
 *
 * 示例 1：
 * 输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * 输出：[[0,1],[1,0],[3,3]]
 * 解释：
 * [0,1] 的皇后可以攻击到国王，因为他们在同一行上。
 * [1,0] 的皇后可以攻击到国王，因为他们在同一列上。
 * [3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。
 * [0,4] 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。
 * [4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。
 * [2,4] 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。
 *
 * 示例 2：
 * 输入：queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
 * 输出：[[2,2],[3,4],[4,4]]
 *
 * 示例 3：
 * 输入：queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
 * 输出：[[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
 *
 * 提示：
 * 1 <= queens.length <= 63
 * queens[i].length == 2
 * 0 <= queens[i][j] < 8
 * king.length == 2
 * 0 <= king[0], king[1] < 8
 * 一个棋盘格上最多只能放置一枚棋子。
 */
public class QueensAttacktheKing {

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        //1，记录皇后的位置
        boolean[][] marks = new boolean[8][8];
        for (int i = 0; i < queens.length; i++) {
            marks[queens[i][0]][queens[i][1]] = true;
        }
        List<List<Integer>> result = new ArrayList<>();
        //2，沿着指定的方向依次查找
        //水平方向向左查找
        for (int i = king[1] - 1; i >= 0; i--) {
            if (marks[king[0]][i]) {
                List<Integer> item = new ArrayList<>();
                item.add(king[0]);
                item.add(i);
                result.add(item);
                break;
            }
        }
        //水平方向向右查找
        for (int i = king[1] + 1; i < 8; i++) {
            if (marks[king[0]][i]) {
                List<Integer> item = new ArrayList<>();
                item.add(king[0]);
                item.add(i);
                result.add(item);
                break;
            }
        }
        //垂直向上查找
        for (int i = king[0] - 1; i >= 0; i--) {
            if (marks[i][king[1]]) {
                List<Integer> item = new ArrayList<>();
                item.add(i);
                item.add(king[1]);
                result.add(item);
                break;
            }
        }
        //垂直向下查找
        for (int i = king[0] + 1; i < 8; i++) {
            if (marks[i][king[1]]) {
                List<Integer> item = new ArrayList<>();
                item.add(i);
                item.add(king[1]);
                result.add(item);
                break;
            }
        }
        //对角左上角
        int x = king[0] - 1;
        int y = king[1] - 1;
        while (x >= 0 && y >= 0) {
            if (marks[x][y]) {
                List<Integer> item = new ArrayList<>();
                item.add(x);
                item.add(y);
                result.add(item);
                break;
            }
            x--;
            y--;
        }
        //对角右上角
        x = king[0] - 1;
        y = king[1] + 1;
        while (x >= 0 && y < 8) {
            if (marks[x][y]) {
                List<Integer> item = new ArrayList<>();
                item.add(x);
                item.add(y);
                result.add(item);
                break;
            }
            x--;
            y++;
        }
        //对角左下角
        x = king[0] + 1;
        y = king[1] - 1;
        while (x < 8 && y >= 0) {
            if (marks[x][y]) {
                List<Integer> item = new ArrayList<>();
                item.add(x);
                item.add(y);
                result.add(item);
                break;
            }
            x++;
            y--;
        }
        //对角右下角
        x = king[0] + 1;
        y = king[1] + 1;
        while (x < 8 && y < 8) {
            if (marks[x][y]) {
                List<Integer> item = new ArrayList<>();
                item.add(x);
                item.add(y);
                result.add(item);
                break;
            }
            x++;
            y++;
        }
        return result;
    }
}

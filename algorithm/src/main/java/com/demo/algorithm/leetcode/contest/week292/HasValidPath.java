package com.demo.algorithm.leetcode.contest.week292;

/**
 * Created by chl on 2022/5/8.
 * description : 检查是否有合法括号字符串路径
 *
 * 一个括号字符串是一个非空且只包含'('和')'的字符串。如果下面任意条件为真，那么这个括号字符串就是合法的。
 *
 * 字符串是()。
 * 字符串可以表示为AB（A连接B），A 和B都是合法括号序列。
 * 字符串可以表示为(A)，其中A是合法括号序列。
 * 给你一个m x n的括号网格图矩阵grid。网格图中一个合法括号路径是满足以下所有条件的一条路径：
 * 路径开始于左上角格子(0, 0)。
 * 路径结束于右下角格子(m - 1, n - 1)。
 * 路径每次只会向 下或者向右移动。
 * 路径经过的格子组成的括号字符串是合法的。
 * 如果网格图中存在一条合法括号路径，请返回true，否则返回false。
 *
 * 示例 1：
 * 输入：grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
 * 输出：true
 * 解释：上图展示了两条路径，它们都是合法括号字符串路径。
 * 第一条路径得到的合法字符串是 "()(())" 。
 * 第二条路径得到的合法字符串是 "((()))" 。
 * 注意可能有其他的合法括号字符串路径。
 *
 * 示例 2：
 * 输入：grid = [[")",")"],["(","("]]
 * 输出：false
 * 解释：两条可行路径分别得到 "))(" 和 ")((" 。由于它们都不是合法括号字符串，我们返回 false 。
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * grid[i][j]要么是'('，要么是')' 。
 */
public class HasValidPath {

    private boolean result = false;
    private boolean[][][] marks;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        //1，处理特殊场景
        if (m * n == 1) {
            return false;
        }
        if (grid[0][0] == ')') {
            return false;
        }
        if (grid[m - 1][n - 1] == '(') {
            return false;
        }
        result = false;
        marks = new boolean[m][n][m + n];
        dfs(0, 0, 1, grid);
        return result;
    }

    private void dfs(int x, int y, int count, char[][] grid) {
        if (result) {
            return;
        }
        int m = grid.length;
        int n = grid[0].length;
        if (x == m - 1 && y == n - 1) {
            if (count == 0) {
                result = true;
            }
            return;
        }
        if (marks[x][y][count]) {
            return;
        }
        marks[x][y][count] = true;
        if (x < m - 1) {
            //向下移动
            if (grid[x + 1][y] == '(') {
                dfs(x + 1, y, count + 1, grid);
            } else {
                if (count > 0) {
                    dfs(x + 1, y, count - 1, grid);
                }
            }
        }
        if (y < n - 1) {
            //向右移动
            if (grid[x][y + 1] == '(') {
                dfs(x, y + 1, count + 1, grid);
            } else {
                if (count > 0) {
                    dfs(x, y + 1, count - 1, grid);
                }
            }
        }
    }
}

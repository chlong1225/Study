package com.demo.algorithm.leetcode.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2022/6/4.
 * description : 面试题08.02. 迷路的机器人
 *
 * 设想有个机器人坐在一个网格的左上角，网格r行c列。机器人只能向下或向右移动，但不能走到一些被禁止的网格（有障碍物）。设计一种算法，寻找机器人从左上角移动到右下角的路径。
 * 网格中的障碍物和空位置分别用1和0来表示。
 * 返回一条可行的路径，路径由经过的网格的行号和列号组成。左上角为0行0列。如果没有可行的路径，返回空数组。
 *
 * 示例1:
 * 输入:
 * [
 *  [0,0,0],
 *  [0,1,0],
 *  [0,0,0]
 * ]
 * 输出: [[0,0],[0,1],[0,2],[1,2],[2,2]]
 * 解释:
 * 输入中标粗的位置即为输出表示的路径，即
 * 0行0列（左上角） -> 0行1列 -> 0行2列 -> 1行2列 -> 2行2列（右下角）
 * 说明：r和c的值均不超过100。
 */
public class PathWithObstacles {

    private List<List<Integer>> result = new ArrayList<>();
    private boolean isFind = false;
    private boolean[][] marks;

    public List<List<Integer>> pathWithObstacles(int[][] obstacleGrid) {
        isFind = false;
        result.clear();
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return result;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        marks = new boolean[m][n];
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return result;
        }
        List<List<Integer>> path = new ArrayList<>();
        List<Integer> item = new ArrayList<>();
        item.add(0);
        item.add(0);
        path.add(item);
        dfs(0, 0, path, obstacleGrid);
        return result;
    }

    private void dfs(int x, int y, List<List<Integer>> path, int[][] obstacleGrid) {
        if (isFind) {
            return;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if (x == m - 1 && y == n - 1) {
            result.addAll(path);
            isFind = true;
            return;
        }
        if (marks[x][y]) {
            return;
        }
        marks[x][y] = true;
        //向右移动
        int nx = x;
        int ny = y + 1;
        if (ny < n && obstacleGrid[nx][ny] == 0) {
            List<Integer> items = new ArrayList<>();
            items.add(nx);
            items.add(ny);
            path.add(items);
            dfs(nx, ny, path, obstacleGrid);
            path.remove(path.size() - 1);
        }
        if (isFind) {
            return;
        }
        //向下移动
        nx = x + 1;
        ny = y;
        if (nx < m && obstacleGrid[nx][ny] == 0) {
            List<Integer> items = new ArrayList<>();
            items.add(nx);
            items.add(ny);
            path.add(items);
            dfs(nx, ny, path, obstacleGrid);
            path.remove(path.size() - 1);
        }
    }
}

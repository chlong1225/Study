package com.demo.algorithm.leetcode.medium;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * create on 2022/2/7
 * @author chenglong
 * description : 黄金矿工
 *
 * 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为m * n的网格grid进行了标注。
 * 每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是0。
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 *
 * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
 * 矿工每次可以从当前位置向上下左右四个方向走。
 * 每个单元格只能被开采（进入）一次。
 * 不得开采（进入）黄金数目为0的单元格。
 * 矿工可以从网格中任意一个有黄金的单元格出发或者是停止。
 *
 * 示例 1：
 * 输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
 * 输出：24
 * 解释：
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * 一种收集最多黄金的路线是：9 -> 8 -> 7。
 *
 * 示例 2：
 * 输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * 输出：28
 * 解释：
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * 一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
 *
 * 提示：
 * 1 <= grid.length,grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * 最多25个单元格中有黄金。
 */
public class GoldMiner {

    //用于将二维数据转换为一维
    private static final int MOD = 16;
    //分别对应左上右下的位置偏移量
    private int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    private int findMax = 0;

    public int getMaximumGold(@NonNull int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //当前位置黄金数量为0时不进入开采
                if (grid[i][j] == 0) {
                    continue;
                }
                int gold = findMaxGold(i, j, grid);
                if (gold > max) {
                    max = gold;
                }
            }
        }
        return max;
    }

    //从位置(i,j)获取最多黄金
    private int findMaxGold(int i, int j, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int start = grid[i][j];
        findMax = start;
        Set<Integer> path = new HashSet<>();
        path.add(i * MOD + j);
        for (int k = 0; k < 4; k++) {
            int nx = i + offsets[k][0];
            int ny = j + offsets[k][1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                //防止偏移越界
                int position = nx * MOD + ny;
                if (path.contains(position)) {
                    //已经访问过的不能再次进入
                    continue;
                }
                if (grid[nx][ny] == 0) {
                    continue;
                }
                path.add(position);
                dfs(nx, ny, start + grid[nx][ny], path, grid);
                path.remove(position);
            }
        }
        return findMax;
    }

    private void dfs(int x, int y, int count, Set<Integer> path, int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean hasNext = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + offsets[i][0];
            int ny = y + offsets[i][1];
            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                //防止偏移越界
                int position = nx * MOD + ny;
                if (path.contains(position)) {
                    //已经访问过的不能再次进入
                    continue;
                }
                if (grid[nx][ny] == 0) {
                    continue;
                }
                path.add(position);
                hasNext = true;
                dfs(nx, ny, count + grid[nx][ny], path, grid);
                path.remove(position);
            }
        }
        if (!hasNext && count > findMax) {
            findMax = count;
        }
    }
}

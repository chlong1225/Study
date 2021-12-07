package com.demo.algorithm.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chl on 2021/12/7.
 * description : 边界着色
 *
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 * 两个网格块属于同一 连通分量 需满足下述全部条件：
 * 两个网格块颜色相同
 * 在上、下、左、右任意一个方向上相邻
 * 连通分量的边界 是指连通分量中满足下述条件之一的所有网格块：
 * 在上、下、左、右四个方向上与不属于同一连通分量的网格块相邻
 * 在网格的边界上（第一行/列或最后一行/列）
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。 
 *
 * 示例 1：
 * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * 输出：[[3,3],[3,2]]
 *
 * 示例 2：
 * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
 * 输出：[[1,3,3],[2,3,3]]
 *
 * 示例 3：
 * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
 * 输出：[[2,2,2],[2,1,2],[2,2,2]]
 *  
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j], color <= 1000
 * 0 <= row < m
 * 0 <= col < n
 */
public class BorderTint {

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        int m = grid.length;
        int n = grid[0].length;
        //用于标记当前位置是否已经被处理
        boolean[][] marks = new boolean[m][n];
        //被比较的颜色,用于判断是否为连通分量
        int compareColor = grid[row][col];
        //记录需要被着色的位置
        List<int[]> tints = new ArrayList<>();
        //定义需要被比较的位置
        List<int[]> datas = new ArrayList<>();
        int[][] offsets = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        datas.add(new int[]{row, col});
        marks[row][col] = true;
        /**
         * 分析: 被着色位置的条件:
         * 1,必须是连通分量
         * 2,上下左右四个方向存在非连通分量块或者在边界
         */
        while (!datas.isEmpty()) {
            //1，从需要检查的数据源中获取位置，并标记当前位置已经被处理
            int[] position = datas.get(datas.size() - 1);
            datas.remove(position);
            //2，检查上下左右是否有连通分量
            int count = 0;
            for (int i = 0; i < offsets.length; i++) {
                int[] offset = offsets[i];
                int nX = position[0] + offset[0];
                int nY = position[1] + offset[1];
                if (nX < 0 || nX >= m || nY < 0 || nY >= n) {
                    //偏移的位置超出边界不处理
                    continue;
                }
                if (grid[nX][nY] == compareColor) {
                    //偏移位置是连通分量
                    count++;
                    if (!marks[nX][nY]) {
                        datas.add(new int[]{nX, nY});
                        marks[nX][nY] = true;
                    }
                }
            }
            if (count != 4) {
                tints.add(position);
            }
        }
        for (int i = 0; i < tints.size(); i++) {
            int[] tint = tints.get(i);
            grid[tint[0]][tint[1]] = color;
        }
        return grid;
    }
}
